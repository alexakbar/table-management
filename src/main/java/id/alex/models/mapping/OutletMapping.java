package id.alex.models.mapping;

import id.alex.enums.TableStatus;
import id.alex.models.EventTable;

import java.util.Date;
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
        public Date created_at;
        public Date updated_at;

        public GetOutlet(){};

        public GetOutlet(String id, String name, String company_id, String table_name, String table_status, Integer table_usage_capacity, Integer table_max_capacity, Date created_at, Date updated_at) {
            this.id = id;
            this.name = name;
            this.company_id = company_id;
            this.table_name = table_name;
            this.table_status = table_status;
            this.table_usage_capacity = table_usage_capacity;
            this.table_max_capacity = table_max_capacity;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ReportUsageTable {
        public static final String MAPPING_NAME = "ReportUsageTable";

        public String id;

        public String name;
        public String company_id;
        public String table_name;
        public Integer total_usage;
        public Integer total_duration;

        public ReportUsageTable(String id, String name, String company_id, String table_name, Integer total_usage, Integer total_duration) {
            this.id = id;
            this.name = name;
            this.company_id = company_id;
            this.table_name = table_name;
            this.total_usage = total_usage;
            this.total_duration = total_duration;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
