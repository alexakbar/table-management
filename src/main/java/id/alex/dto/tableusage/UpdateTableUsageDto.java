package id.alex.dto.tableusage;

public class UpdateTableUsageDto {
    public String table_id;
    public Integer capacity;
    public Integer duration;
    public boolean is_active;

    public UpdateTableUsageDto(){};

    public UpdateTableUsageDto(String table_id, Integer capacity, Integer duration, boolean is_active) {
        this.table_id = table_id;
        this.capacity = capacity;
        this.duration = duration;
        this.is_active = is_active;
    }
}
