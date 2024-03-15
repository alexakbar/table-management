package id.alex.models;

import id.alex.enums.TableStatus;
import id.alex.models.mapping.CompanyMapping;
import id.alex.models.mapping.EventTableMapping;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@SqlResultSetMapping(
        name = EventTableMapping.GetEventTable.MAPPING_NAME,
        classes = @ConstructorResult(
                targetClass= EventTableMapping.GetEventTable.class,
                columns={
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "outlet_id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "total_duration"),
                        @ColumnResult(name = "status"),
                        @ColumnResult(name = "usage_capacity"),
                        @ColumnResult(name = "max_capacity"),
                        @ColumnResult(name = "total_usage")
                }
        )
)

@Entity
@Table(name = "event_tables")
    public class EventTable extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    public String id;

    @Column(name = "outlet_id")
    public String outletId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outlet_id", insertable = false,updatable = false)
    private Outlet outlet;

    @Column(name = "name")
        public String name;

    @Column(name = "total_duration")
    public Integer total_duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public TableStatus tableStatus;

    @Column(name = "usage_capacity")
    public Integer usage_capacity;

    @Column(name = "max_capacity")
    public Integer max_capacity;

    @Column(name = "total_usage")
    public Integer total_usage;

    public EventTable() {}

    public EventTable(String id, String outletId, Outlet outlet, String name, Integer total_duration, TableStatus tableStatus, Integer usage_capacity, Integer max_capacity, Integer total_usage) {
        this.id = id;
        this.outletId = outletId;
        this.outlet = outlet;
        this.name = name;
        this.total_duration = total_duration;
        this.tableStatus = tableStatus;
        this.usage_capacity = usage_capacity;
        this.max_capacity = max_capacity;
        this.total_usage = total_usage;
    }
}
