package id.alex.handlers;

import id.alex.dto.response.ResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ResponseHandler {


    public Response response(Response.Status status) {
        return getResponse(status);
    }

    public Response response(Response.Status status, List<?> data) {
        if (data != null){
            ResponseDto responseDto = new ResponseDto();
            if (status == Response.Status.OK) {
                responseDto.err = false;
                responseDto.msg = "Success";
                responseDto.data = data;
                return Response.status(status).entity(responseDto).build();
            }
        }

        return getResponse(status);
    }
    public Response response(Response.Status status, Object singleObject) {
        if (singleObject != null) { // Check if singleObject is not null
            ResponseDto responseDto = new ResponseDto();
            if (status == Response.Status.OK) {
                responseDto.err = false;
                responseDto.msg = "Success";
                responseDto.data = singleObject;
                return Response.status(status).entity(responseDto).build();
            }
        } else { // Handle null singleObject case
            ResponseDto responseDto = new ResponseDto();
            responseDto.err = false;
            responseDto.msg = "Success, but no data found";
            responseDto.data = null; // Or any appropriate handling for null data
            return Response.status(status).entity(responseDto).build();
        }

        return getResponse(status);
    }

    private Response getResponse(Response.Status status) {
        ResponseDto responseDto = new ResponseDto();
        if (status == Response.Status.OK) {
            responseDto.err = false;
            responseDto.msg = "Success";
        } else {
            responseDto.err = true;
            responseDto.msg = "Error";
        }

        return  Response.status(status).entity(responseDto).build();
    }
}
