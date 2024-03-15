package id.alex.dto.tableusage;

import id.alex.models.EventTable;
import jakarta.persistence.*;

public class GetTableUsageDto {

    public static class Request {
        public String id;
        public String table_id;
        public int capacity;
        public int duration;
        public boolean is_active;
    }

    public String id;
    public String table_id;
    public int capacity;
    public int duration;
    public boolean is_active;

    public GetTableUsageDto(){};

    public GetTableUsageDto(String id, String tableId, int capacity, int duration, boolean is_active) {
        this.id = id;
        this.table_id = tableId;
        this.capacity = capacity;
        this.duration = duration;
        this.is_active = is_active;
    }
}
