package id.alex.dto.eventtable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddEventTableDto {

    @NotBlank(message = "name is required")
    public String name;
    @NotNull(message = "Max Capacity is required")
    public int max_capacity;
    @NotNull(message = "Outlet ID is required")
    public String outlet_id;

    public AddEventTableDto(){};

    public AddEventTableDto(String name, int max_capacity, String outletId) {
        this.name = name;
        this.max_capacity = max_capacity;
        this.outlet_id = outletId;
    }
}
