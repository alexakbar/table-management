package id.alex.models.mapping;

public class TableUsageMapping {
    public static class GetTableUsage {
        public static final String MAPPING_NAME = "GetTableUsage";

        public String id;
        public String table_id;
        public int capacity;
        public int duration;
        public boolean is_active;

        public GetTableUsage(String id, String table_id, int capacity, int duration, boolean is_active) {
            this.id = id;
            this.table_id = table_id;
            this.capacity = capacity;
            this.duration = duration;
            this.is_active = is_active;
        }
    }
}
