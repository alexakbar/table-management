package id.alex.handlers;

import id.alex.dto.response.ResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ResponseHandler {


    public Response error(String msg) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.err = true;
        responseDto.msg = msg;
        return  Response.status(Response.Status.BAD_REQUEST).entity(responseDto).build();
    }
    public Response error(Response.Status status,String msg) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.err = true;
        responseDto.msg = msg;
        return  Response.status(status).entity(responseDto).build();
    }

    public Response success(String msg) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.err = false;
        responseDto.msg = msg;
        return  Response.status(Response.Status.OK).entity(responseDto).build();
    }

    public Response success(Object singleObject) {
        if (singleObject != null) { // Check if singleObject is not null
            ResponseDto responseDto = new ResponseDto();
            responseDto.err = false;
            responseDto.msg = "Success";
            responseDto.data = singleObject;
            return Response.status(Response.Status.OK).entity(responseDto).build();
        } else { // Handle null singleObject case
            ResponseDto responseDto = new ResponseDto();
            responseDto.err = false;
            responseDto.msg = "Success, but no data found";
            responseDto.data = null; // Or any appropriate handling for null data
            return Response.status(Response.Status.OK).entity(responseDto).build();
        }
    }

    public Response success(List<?> data) {
        ResponseDto responseDto = new ResponseDto();

        responseDto.err = false;
        responseDto.msg = "Success";
        responseDto.data = data;
        return Response.status(Response.Status.OK).entity(responseDto).build();
    }

    public Response success() {
        ResponseDto responseDto = new ResponseDto();

        responseDto.err = false;
        responseDto.msg = "Success";
        return Response.status(Response.Status.OK).entity(responseDto).build();
    }
}
