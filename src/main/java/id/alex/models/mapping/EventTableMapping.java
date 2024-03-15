package id.alex.models.mapping;

import id.alex.enums.TableStatus;

public class EventTableMapping {

    public static class GetEventTable {
        public static final String MAPPING_NAME = "GetEventTable";

        public String id;
        private String outlet_id;
        public String name;
        public int total_duration;
        public String status;
        public int usage_capacity;
        public int max_capacity;
        public int total_usage;

        public GetEventTable(String id, String outlet_id, String name, int total_duration, String status, int usage_capacity, int max_capacity, int total_usage) {
            this.id = id;
            this.outlet_id = outlet_id;
            this.name = name;
            this.total_duration = total_duration;
            this.status = status;
            this.usage_capacity = usage_capacity;
            this.max_capacity = max_capacity;
            this.total_usage = total_usage;
        }
    }
}
