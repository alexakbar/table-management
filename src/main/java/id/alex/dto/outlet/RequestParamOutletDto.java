package id.alex.dto.outlet;

import jakarta.ws.rs.DefaultValue;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.reactive.RestQuery;

public class RequestParamOutletDto {
    @Parameter
    @RestQuery
    @DefaultValue("")
    public String name;
    @Parameter
    @RestQuery
    @DefaultValue("")
    public String company_id;
}
