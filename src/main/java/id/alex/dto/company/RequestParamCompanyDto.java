package id.alex.dto.company;

import jakarta.ws.rs.DefaultValue;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.reactive.RestQuery;

public class RequestParamCompanyDto {
    @Parameter
    @RestQuery
    @DefaultValue("")
    public String name;
    @Parameter
    @RestQuery
    @DefaultValue("")
    public String address;
    @Parameter
    @RestQuery
    @DefaultValue("")
    public String tlp;
}
