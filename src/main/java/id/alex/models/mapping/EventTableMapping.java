package id.alex.models.mapping;

import id.alex.enums.TableStatus;

import java.util.Date;

public class EventTableMapping {

    public static class GetEventTable {
        public static final String MAPPING_NAME = "GetEventTable";

        public String id;
        private String outlet_id;
        public String name;
        public Integer total_duration;
        public String status;
        public Integer usage_capacity;
        public Integer max_capacity;
        public Integer total_usage;
        public Date created_at;
        public Date updated_at;

        public GetEventTable(String id, String outlet_id, String name, Integer total_duration, String status, Integer usage_capacity, Integer max_capacity, Integer total_usage, Date created_at, Date updated_at) {
            this.id = id;
            this.outlet_id = outlet_id;
            this.name = name;
            this.total_duration = total_duration;
            this.status = status;
            this.usage_capacity = usage_capacity;
            this.max_capacity = max_capacity;
            this.total_usage = total_usage;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }
    }
}
