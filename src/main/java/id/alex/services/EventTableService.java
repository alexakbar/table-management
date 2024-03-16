package id.alex.services;

import id.alex.dao.EventTableDao;
import id.alex.dto.eventtable.AddEventTableDto;
import id.alex.dto.eventtable.GetEventTableDto;
import id.alex.dto.eventtable.UpdateEventTableDto;
import id.alex.handlers.ResponseHandler;
import id.alex.handlers.ValidationHandlerException;
import id.alex.helpers.ValidateRequest;
import id.alex.models.mapping.EventTableMapping;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EventTableService {
    @Inject
    ValidateRequest validateRequest;
    @Inject
    EventTableDao eventTableDao;
    @Inject
    ResponseHandler responseHandler;

    public List<GetEventTableDto> getAll() {
        return eventTableDao.getAll();
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
}
