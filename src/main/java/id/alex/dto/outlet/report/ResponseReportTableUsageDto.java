package id.alex.dto.outlet.report;

import id.alex.dto.outlet.GetOutletDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseReportTableUsageDto {
    public static class Response {
        public String id;
        public String name;
        public String company_id;
        public List<TableEvent> table_events;

        public Response() {
            this.table_events = new ArrayList<>();
        }
    }

    public static class TableEvent {
        public String name;
        public Integer total_usage;
        public Integer total_duration;
    }
}
