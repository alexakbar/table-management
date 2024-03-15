package id.alex.models;

import id.alex.models.mapping.CompanyMapping;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@SqlResultSetMapping(
        name = CompanyMapping.GetCompany.MAPPING_NAME,
        classes = @ConstructorResult(
                targetClass= CompanyMapping.GetCompany.class,
                columns={
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "address"),
                        @ColumnResult(name = "tlp"),
                }
        )
)

@Entity
@Table(name = "companies")
public class Company extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    public String id;

    @Column(name = "name")
    public String name;

    @Column(name = "address")
    public String address;

    @Column(name = "tlp")
    public  String tlp;
}