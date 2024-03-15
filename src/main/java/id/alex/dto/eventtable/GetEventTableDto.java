package id.alex.dto.eventtable;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.alex.enums.TableStatus;
import id.alex.models.Outlet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;

public class GetEventTableDto {

    public String id;
    private String outlet_id;
    public String name;
    public int total_duration;
    public TableStatus status;
    public int usage_capacity;
    public int max_capacity;
    public int total_usage;

    public GetEventTableDto(){};

    public GetEventTableDto(String id, String outlet_id, String name, int total_duration, TableStatus tabletStatus, int usage_capacity, int max_capacity, int total_usage) {
        this.id = id;
        this.outlet_id = outlet_id;
        this.name = name;
        this.total_duration = total_duration;
        this.status = tabletStatus;
        this.usage_capacity = usage_capacity;
        this.max_capacity = max_capacity;
        this.total_usage = total_usage;
    }
}
