package id.alex.services;

import id.alex.dao.EventTableDao;
import id.alex.dto.eventtable.*;
import id.alex.enums.TableStatus;
import id.alex.handlers.ResponseHandler;
import id.alex.handlers.ValidationHandlerException;
import id.alex.helpers.ValidateRequest;
import id.alex.models.mapping.EventTableMapping;
import id.alex.models.mapping.TableUsageMapping;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class EventTableService {
    @Inject
    ValidateRequest validateRequest;
    @Inject
    EventTableDao eventTableDao;
    @Inject
    TableUsageService tableUsageService;
    @Inject
    ResponseHandler responseHandler;

    public List<GetEventTableDto> getAll(RequestParamEventTableDto request) {
        return eventTableDao.getAll(request);
    }

    public EventTableMapping.GetEventTable findById(String Id){
        return eventTableDao.findById(Id);
    }

    public void create(AddEventTableDto request) throws ValidationHandlerException {
        validateRequest.validate(request);
        eventTableDao.create(request);
    }

    public void update(String id, UpdateEventTableDto request) {
        eventTableDao.update(id,request);
    }

    public void delete(String id) {
        eventTableDao.delete(id);
    }

    @Transactional
    public Response changeStatus(String id, ChangeStatusEventTableDto request) {
        TableUsageMapping.GetTableUsage activeTableUsage = tableUsageService.findActiveByIdTable(id);
        if (activeTableUsage != null && request.status == TableStatus.available) {
           Response finishTable = tableUsageService.finishTable(activeTableUsage.id);

           eventTableDao.changeStatus(id, request);
           return finishTable;
        }

        eventTableDao.changeStatus(id, request);
        return  responseHandler.success();
    }

}
