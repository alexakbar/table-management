package id.alex.services;

import id.alex.dao.OutletDao;
import id.alex.dao.TableUsageDao;
import id.alex.dto.eventtable.UpdateEventTableDto;
import id.alex.dto.outlet.GetOutletDto;
import id.alex.dto.outlet.RequestOutletDto;
import id.alex.dto.tableusage.*;
import id.alex.enums.TableStatus;
import id.alex.handlers.ResponseHandler;
import id.alex.handlers.ValidationHandlerException;
import id.alex.helpers.ValidateRequest;
import id.alex.models.mapping.EventTableMapping;
import id.alex.models.mapping.TableUsageMapping;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class TableUsageService {
    @Inject
    ValidateRequest validateRequest;
    @Inject
    TableUsageDao tableUsageDao;
    @Inject
    EventTableService eventTableService;

    @Inject
    ResponseHandler responseHandler;

    public List<GetTableUsageDto> getAll(RequestParamTableUsageDto request) {
        return  tableUsageDao.getAll(request);
    }

    public TableUsageMapping.GetTableUsage findById(String Id){
        return tableUsageDao.findById(Id);
    }

    public void create(AddTableUsageDto request) throws ValidationHandlerException {
        validateRequest.validate(request);
        tableUsageDao.create(request);
    }

    public void update(String id, UpdateTableUsageDto request) {
        tableUsageDao.update(id,request);
    }

    public void delete(String id) {
        tableUsageDao.delete(id);
    }

    public Response useTable(UseTableUsageDto request) {
        validateRequest.validate(request);

        EventTableMapping.GetEventTable eventTable = eventTableService.findById(request.table_id);
        if (eventTable == null) {
            throw new ValidationHandlerException(Response.Status.NOT_FOUND);
        }

        if (request.capacity > eventTable.max_capacity) {
            return responseHandler.error("Max Capacity");
        }

        if (!eventTable.status.equals("available") ) {
            return responseHandler.error("Table Used");
        }

        // create table usage
        AddTableUsageDto addTableUsageDto = new AddTableUsageDto();
        addTableUsageDto.table_id = eventTable.id;
        addTableUsageDto.is_active = true;
        addTableUsageDto.capacity = request.capacity;
        tableUsageDao.create(addTableUsageDto);

        // update master table
        UpdateEventTableDto updateEventTableDto = new UpdateEventTableDto();
        updateEventTableDto.usage_capacity = request.capacity;
        updateEventTableDto.status = TableStatus.used;
        eventTableService.update(eventTable.id, updateEventTableDto);

        return responseHandler.success();
    }

    public Response finishTable(String id) {
        TableUsageMapping.GetTableUsage tableUsage = tableUsageDao.findById(id);

        if (tableUsage == null) {
            throw new ValidationHandlerException(Response.Status.NOT_FOUND);
        }

        EventTableMapping.GetEventTable eventTable = eventTableService.findById(tableUsage.table_id);
        if (eventTable == null) {
            throw new ValidationHandlerException(Response.Status.NOT_FOUND);
        }

        if (!tableUsage.is_active) {
            return responseHandler.error("Table Already Not Active");
        }

        Instant now = Instant.now();
        Instant createdAt = tableUsage.created_at.toInstant();

        // sum duration usage
        Duration durationUsage = Duration.between(createdAt, now);
        int hours = Math.toIntExact(durationUsage.toHoursPart());

        // active false
        UpdateTableUsageDto updateTableUsageDto = new UpdateTableUsageDto();
        updateTableUsageDto.is_active = false;
        updateTableUsageDto.duration = hours;
        tableUsageDao.update(id, updateTableUsageDto);

        // update master table
        UpdateEventTableDto updateEventTableDto = new UpdateEventTableDto();
        updateEventTableDto.status = TableStatus.available;
        updateEventTableDto.total_usage =  (eventTable.total_usage != null ? eventTable.total_usage   : 0) + 1;
        updateEventTableDto.usage_capacity = 0;
        updateEventTableDto.total_duration = (eventTable.total_duration != null ? eventTable.total_duration   : 0) + hours;
        eventTableService.update(tableUsage.table_id, updateEventTableDto);

        return responseHandler.success();
    }
}
