package id.alex.controllers;

import id.alex.dto.eventtable.AddEventTableDto;
import id.alex.dto.eventtable.GetEventTableDto;
import id.alex.dto.eventtable.UpdateEventTableDto;
import id.alex.dto.tableusage.*;
import id.alex.handlers.ResponseHandler;
import id.alex.models.mapping.TableUsageMapping;
import id.alex.services.EventTableService;
import id.alex.services.TableUsageService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("table-usage")
@Produces(MediaType.APPLICATION_JSON)
public class TableUsageController {
    @Inject
    TableUsageService tableUsageService;
    @Inject
    ResponseHandler responseHandler;

    @GET
    public Response getAll(@BeanParam RequestParamTableUsageDto request)
    {
        List<GetTableUsageDto> list = tableUsageService.getAll(request);
        return responseHandler.success(list);
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") String id) {
        TableUsageMapping.GetTableUsage list = tableUsageService.findById(id);
        return responseHandler.success(list);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AddTableUsageDto request) {
        tableUsageService.create(request);
        return responseHandler.success();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") String id, UpdateTableUsageDto request) {
        tableUsageService.update(id, request);
        return responseHandler.success();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        tableUsageService.delete(id);
        return responseHandler.success();
    }

    @POST
    @Path("use-table")
    @Transactional
    public Response useTable(UseTableUsageDto request) {
        return tableUsageService.useTable(request);
    }

    @POST
    @Path("{id}/finish-table")
    @Transactional
    public Response finishTable(@PathParam("id") String id) {
        return tableUsageService.finishTable(id);
    }
}
