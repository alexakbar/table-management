package id.alex.dto.eventtable;

import id.alex.enums.TableStatus;
import jakarta.validation.constraints.NotBlank;

public class UpdateEventTableDto {
    public String name;
    public Integer max_capacity;
    public Integer total_duration;
    public TableStatus status;
    public Integer usage_capacity;
    public Integer total_usage;



    public UpdateEventTableDto() {};

    public UpdateEventTableDto(String name, Integer max_capacity, Integer total_duration, TableStatus status, Integer usage_capacity, Integer total_usage) {
        this.name = name;
        this.max_capacity = max_capacity;
        this.total_duration = total_duration;
        this.status = status;
        this.usage_capacity = usage_capacity;
        this.total_usage = total_usage;
    }
}
