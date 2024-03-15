package id.alex.handlers;

import jakarta.ws.rs.core.Response;

public class ValidationHandlerException extends RuntimeException {
    public Response.Status status;

    public ValidationHandlerException(String str) {
        this(str,Response.Status.BAD_REQUEST);
    }

    public ValidationHandlerException(String str,Response.Status status){
        super(str);
        this.status = status;
    }

    public ValidationHandlerException(Response.Status status){
        this(status.name(),status);
    }
}

