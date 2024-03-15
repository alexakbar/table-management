package id.alex.services;

import id.alex.dao.OutletDao;
import id.alex.dao.TableUsageDao;
import id.alex.dto.eventtable.UpdateEventTableDto;
import id.alex.dto.outlet.GetOutletDto;
import id.alex.dto.outlet.RequestOutletDto;
import id.alex.dto.tableusage.AddTableUsageDto;
import id.alex.dto.tableusage.GetTableUsageDto;
import id.alex.dto.tableusage.UpdateTableUsageDto;
import id.alex.dto.tableusage.UseTableUsageDto;
import id.alex.enums.TableStatus;
import id.alex.handlers.ValidationHandlerException;
import id.alex.helpers.ValidateRequest;
import id.alex.models.mapping.EventTableMapping;
import id.alex.models.mapping.TableUsageMapping;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class TableUsageService {
    @Inject
    ValidateRequest validateRequest;
    @Inject
    TableUsageDao tableUsageDao;
    @Inject
    EventTableService eventTableService;

    public List<GetTableUsageDto> getAll() {
        return  tableUsageDao.getAll();
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

    public void useTable(UseTableUsageDto request) {
        validateRequest.validate(request);

        EventTableMapping.GetEventTable eventTable = eventTableService.findById(request.table_id);
        if (eventTable == null) {
            throw new ValidationHandlerException(Response.Status.NOT_FOUND);
        }

        if (request.capacity > eventTable.max_capacity) {
            throw new ValidationHandlerException("Max Capacity");
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
    }

    public void finishTable(String id) {
        TableUsageMapping.GetTableUsage tableUsage = tableUsageDao.findById(id);

        if (tableUsage == null) {
            throw new ValidationHandlerException(Response.Status.NOT_FOUND);
        }

        EventTableMapping.GetEventTable eventTable = eventTableService.findById(tableUsage.table_id);
        if (eventTable == null) {
            throw new ValidationHandlerException(Response.Status.NOT_FOUND);
        }

        // active false
        UpdateTableUsageDto updateTableUsageDto = new UpdateTableUsageDto();
        updateTableUsageDto.is_active = false;
        updateTableUsageDto.duration = 10;
        tableUsageDao.update(id, updateTableUsageDto);

        // update master table
        UpdateEventTableDto updateEventTableDto = new UpdateEventTableDto();
        updateEventTableDto.status = TableStatus.available;
        updateEventTableDto.total_usage =  eventTable.total_usage + 1;
        updateEventTableDto.usage_capacity = 0;
        updateEventTableDto.total_duration = eventTable.total_duration + 10;
        eventTableService.update(tableUsage.table_id, updateEventTableDto);
    }
}
