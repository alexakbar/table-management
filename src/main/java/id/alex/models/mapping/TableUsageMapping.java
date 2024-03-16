package id.alex.models.mapping;

import java.util.Date;

public class TableUsageMapping {
    public static class GetTableUsage {
        public static final String MAPPING_NAME = "GetTableUsage";

        public String id;
        public String table_id;
        public Integer capacity;
        public Integer duration;
        public boolean is_active;
        public Date created_at;
        public Date updated_at;

        public GetTableUsage(String id, String table_id, Integer capacity, Integer duration, boolean is_active, Date created_at, Date updated_at) {
            this.id = id;
            this.table_id = table_id;
            this.capacity = capacity;
            this.duration = duration;
            this.is_active = is_active;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }
    }
}
