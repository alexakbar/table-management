package id.alex.models.mapping;

import io.smallrye.common.constraint.NotNull;

public class CompanyMapping {


    public static class GetCompany {
        public static final String MAPPING_NAME = "GetCompany";

        public String id;
        public String name;
        public String address;
        public String tlp;

        public GetCompany(String id, String name, String address, String tlp) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.tlp = tlp;
        }
    }
}
