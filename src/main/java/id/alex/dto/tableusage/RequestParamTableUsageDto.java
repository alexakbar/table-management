package id.alex.dto.tableusage;

import jakarta.ws.rs.DefaultValue;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.reactive.RestQuery;

public class RequestParamTableUsageDto {
    @Parameter
    @RestQuery
    @DefaultValue("")
    public String table_id;

    @Parameter
    @RestQuery
    @DefaultValue("false")
    public boolean is_active;
}
