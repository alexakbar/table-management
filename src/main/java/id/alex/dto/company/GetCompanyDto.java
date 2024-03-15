package id.alex.dto.company;

import io.smallrye.common.constraint.NotNull;

public class GetCompanyDto {

    @NotNull
    public String id;
    @NotNull

    public String name;

    public GetCompanyDto() {};
    public GetCompanyDto(String  id, String name) {
        this.id = id;
        this.name = name;
    }

}
