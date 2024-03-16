package id.alex.controllers;

import id.alex.dto.company.GetCompanyDto;
import id.alex.dto.company.RequestCompanyDto;
import id.alex.dto.eventtable.AddEventTableDto;
import id.alex.dto.eventtable.GetEventTableDto;
import id.alex.dto.eventtable.UpdateEventTableDto;
import id.alex.handlers.ResponseHandler;
import id.alex.models.mapping.EventTableMapping;
import id.alex.services.EventTableService;
import id.alex.services.OutletService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("event-table")
@Produces(MediaType.APPLICATION_JSON)
public class EventTableController {

    @Inject
    EventTableService eventTableService;
    @Inject
    ResponseHandler responseHandler;

    @GET
    public Response getAll()
    {
        List<GetEventTableDto> list = eventTableService.getAll();
        return responseHandler.success(list);
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") String id) {
        EventTableMapping.GetEventTable table_usage = eventTableService.findById(id);
        return responseHandler.success(table_usage);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AddEventTableDto request) {
        eventTableService.create(request);
        return responseHandler.success();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") String id, UpdateEventTableDto request) {
        eventTableService.update(id, request);
        return responseHandler.success();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id){
        eventTableService.delete(id);
        return responseHandler.success();
    }
}
