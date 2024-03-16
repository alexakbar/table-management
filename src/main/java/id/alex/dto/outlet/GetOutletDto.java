package id.alex.dto.outlet;

import id.alex.models.EventTable;
import id.alex.models.TableUsage;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetOutletDto {

    public static class Response {
        public String id;
        public String name;
        public String company_id;

        public List<TableEvent> tableEvents;

        public Date created_at;
        public Date updated_at;

        public Response() {
            this.tableEvents = new ArrayList<>();
        }
    }

    public static class TableEvent {
        public String table_name;
        public String table_status;
        public Integer table_usage_capacity;
        public Integer table_max_capacity;

        public Date created_at;
        public Date updated_At;
    }
}
