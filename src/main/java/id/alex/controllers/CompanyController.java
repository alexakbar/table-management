package id.alex.controllers;

import id.alex.dto.company.RequestCompanyDto;
import id.alex.dto.company.GetCompanyDto;
import id.alex.handlers.ResponseHandler;
import id.alex.models.mapping.CompanyMapping;
import id.alex.services.CompanyService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyController {
    @Inject CompanyService companyService;

    @Inject ResponseHandler responseHandler;

    @GET
    public Response getAll()
    {
        List<GetCompanyDto> list = companyService.getAll();
        return responseHandler.response(Response.Status.OK, list);
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") String id) {
        CompanyMapping.GetCompany list = companyService.findById(id);
        return responseHandler.response(Response.Status.OK, list);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCompany(RequestCompanyDto request) {
        companyService.create(request);
        return responseHandler.response(Response.Status.OK);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") String id, RequestCompanyDto request) {
        companyService.updateCompany(id, request);
        return responseHandler.response(Response.Status.OK);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id){
        companyService.delete(id);
        return responseHandler.response(Response.Status.OK);
    }
}
