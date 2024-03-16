package id.alex.models;

import id.alex.models.mapping.CompanyMapping;
import id.alex.models.mapping.OutletMapping;
import id.alex.services.OutletService;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@SqlResultSetMapping(
        name = OutletMapping.GetOutlet.MAPPING_NAME,
        classes = @ConstructorResult(
                targetClass= OutletMapping.GetOutlet.class,
                columns={
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "company_id"),
                        @ColumnResult(name = "table_name"),
                        @ColumnResult(name = "table_status"),
                        @ColumnResult(name = "table_usage_capacity",  type = Integer.class),
                        @ColumnResult(name = "table_max_capacity", type = Integer.class),
                }
        )
)

@Entity
@Table(name = "outlets")
public class Outlet extends BaseModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    public String id;

    @Column(name = "company_id")
    public String companyId;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",insertable = false,updatable = false)
    private Company company;

    @Column(name = "name")
    public String name;

    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL)
    private List<EventTable> tables;




}
