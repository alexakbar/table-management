package id.alex.dto.tableusage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UseTableUsageDto {
    @NotBlank(message = "Table ID is required")
    public String table_id;
    @NotNull(message = "Capacity is required")
    public Integer capacity;

    public UseTableUsageDto(){};

    public UseTableUsageDto(String table_id, Integer capacity) {
        this.table_id = table_id;
        this.capacity = capacity;
    }
}
