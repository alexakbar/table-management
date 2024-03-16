package id.alex.models.mapping;

import io.smallrye.common.constraint.NotNull;

import java.util.Date;

public class CompanyMapping {


    public static class GetCompany {
        public static final String MAPPING_NAME = "GetCompany";

        public String id;
        public String name;
        public String address;
        public String tlp;
        public Date created_at;
        public Date updated_at;

        public GetCompany(String id, String name, String address, String tlp, Date created_at, Date updated_at) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.tlp = tlp;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }
    }
}
