package id.alex.dto.eventtable;

import jakarta.validation.constraints.NotBlank;

public class UpdateEventTableDto {
    public String name;
    public int max_capacity;

    public UpdateEventTableDto() {};

    public UpdateEventTableDto(String name, int max_capacity) {
        this.name = name;
        this.max_capacity = max_capacity;
    }
}
