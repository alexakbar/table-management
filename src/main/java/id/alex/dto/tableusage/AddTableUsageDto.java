package id.alex.dto.tableusage;

public class AddTableUsageDto {
    public String table_id;
    public int capacity;
    public int duration;
    public boolean is_active;

    public AddTableUsageDto() {};

    public AddTableUsageDto(String table_id, int capacity, int duration, boolean is_active) {
        this.table_id = table_id;
        this.capacity = capacity;
        this.duration = duration;
        this.is_active = is_active;
    }
}
