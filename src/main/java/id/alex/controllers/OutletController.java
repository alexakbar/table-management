package id.alex.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.alex.dto.company.GetCompanyDto;
import id.alex.dto.company.RequestCompanyDto;
import id.alex.dto.outlet.GetOutletDto;
import id.alex.dto.outlet.RequestOutletDto;
import id.alex.handlers.ResponseHandler;
import id.alex.models.mapping.OutletMapping;
import id.alex.services.OutletService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("outlet")
@Produces(MediaType.APPLICATION_JSON)
public class OutletController {

    @Inject
    OutletService outletService;
    @Inject
    ResponseHandler responseHandler;

    @GET
    public Response getAll() {
        List<GetOutletDto.Response> list = outletService.getAll();
        return responseHandler.response(Response.Status.OK,list);
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") String id) {
        List<GetOutletDto.Response> list = outletService.findById(id);
        return responseHandler.response(Response.Status.OK, list);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(RequestOutletDto request) {
        outletService.create(request);
        return responseHandler.response(Response.Status.OK);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") String id, RequestOutletDto request) {
        outletService.updateCompany(id, request);
        return responseHandler.response(Response.Status.OK);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id){
        outletService.delete(id);
        return responseHandler.response(Response.Status.OK);
    }
}
