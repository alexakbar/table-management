package id.alex.dto.outlet;

import jakarta.validation.constraints.NotBlank;

public class RequestOutletDto {

    @NotBlank(message = "name is required")
    public String name;
    public String company_id;

    public RequestOutletDto() {};
    public RequestOutletDto(String name, String companyId) {
        this.name = name;
        this.company_id = companyId;
    }

}
