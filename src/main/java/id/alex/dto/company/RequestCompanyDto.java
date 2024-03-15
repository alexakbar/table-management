package id.alex.dto.company;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public class RequestCompanyDto {

    @NotBlank(message = "name is required")
    public String name;
    public String address;
    public  String tlp;

    public RequestCompanyDto(){};

    public RequestCompanyDto(String name, String address, String tlp) {
        this.name = name;
        this.address = address;
        this.tlp = tlp;
    }
}
