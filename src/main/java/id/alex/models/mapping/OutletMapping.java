package id.alex.models.mapping;

import id.alex.enums.TableStatus;
import id.alex.models.EventTable;

import java.util.List;

public class OutletMapping {

    public static class GetOutlet {
        public static final String MAPPING_NAME = "GetOutlet";

        public String id;
        public String name;
        public String company_id;
        public String table_name;
        public String table_status;
        public Integer table_usage_capacity;
        public Integer table_max_capacity;

        public GetOutlet(){};

        public GetOutlet(String id, String name, String company_id, String table_name, String table_status, Integer table_usage_capacity, Integer table_max_capacity) {
            this.id = id;
            this.name = name;
            this.company_id = company_id;
            this.table_name = table_name;
            this.table_status = table_status;
            this.table_usage_capacity = table_usage_capacity;
            this.table_max_capacity = table_max_capacity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
