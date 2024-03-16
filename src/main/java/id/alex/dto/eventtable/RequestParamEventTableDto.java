package id.alex.dto.eventtable;

import id.alex.enums.TableStatus;
import jakarta.ws.rs.DefaultValue;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.reactive.RestQuery;

public class RequestParamEventTableDto {
    @Parameter
    @RestQuery
    @DefaultValue("")
    public String name;

    @Parameter
    @RestQuery
    @DefaultValue("")
    public String outlet_id;

    @Parameter
    @RestQuery
    @DefaultValue("available")
    public TableStatus status;
}
