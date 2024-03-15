package id.alex.models;

import id.alex.models.mapping.EventTableMapping;
import id.alex.models.mapping.TableUsageMapping;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@SqlResultSetMapping(
        name = TableUsageMapping.GetTableUsage.MAPPING_NAME,
        classes = @ConstructorResult(
                targetClass= TableUsageMapping.GetTableUsage.class,
                columns={
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "table_id"),
                        @ColumnResult(name = "capacity"),
                        @ColumnResult(name = "duration"),
                        @ColumnResult(name = "is_active"),
                }
        )
)

@Entity
@Table(name = "table_usages")
public class
TableUsage extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    public String id;

    @Column(name = "table_id")
    public String tableId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", insertable = false,updatable = false)
    public EventTable eventTable;

    @Column(name = "capacity")
    public int capacity;

    @Column(name = "duration")
    public int duration;

    @Column(name = "is_active")
    public boolean is_active;
}
