package id.alex.dto.company;

import io.smallrye.common.constraint.NotNull;

public class GetCompanyDto {

    @NotNull
    public String id;
    @NotNull
    public String name;

    public String address;

    public String tlp;

    public GetCompanyDto() {};

    public GetCompanyDto(String id, String name, String address, String tlp) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tlp = tlp;
    }
}
