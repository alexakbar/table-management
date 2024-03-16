package id.alex.dto.outlet;

import jakarta.validation.constraints.NotBlank;

public class AddOutletDto {
    @NotBlank(message = "name is required")
    public String name;
    @NotBlank(message = "Company ID is required")
    public String company_id;

    public  AddOutletDto(){};
    public AddOutletDto(String name, String company_id) {
        this.name = name;
        this.company_id = company_id;
    }
}
