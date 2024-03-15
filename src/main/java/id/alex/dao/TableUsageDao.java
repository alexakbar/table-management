package id.alex.dao;

import id.alex.dto.eventtable.AddEventTableDto;
import id.alex.dto.eventtable.GetEventTableDto;
import id.alex.dto.eventtable.UpdateEventTableDto;
import id.alex.dto.tableusage.AddTableUsageDto;
import id.alex.dto.tableusage.GetTableUsageDto;
import id.alex.dto.tableusage.UpdateTableUsageDto;
import id.alex.dto.tableusage.UseTableUsageDto;
import id.alex.models.EventTable;
import id.alex.models.TableUsage;
import id.alex.models.mapping.EventTableMapping;
import id.alex.models.mapping.TableUsageMapping;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TableUsageDao {
    @Inject
    EntityManager entityManager;
    public List<GetTableUsageDto> getAll() {
        String q = "SELECT * FROM table_usages";
        return entityManager.createNativeQuery(q, TableUsageMapping.GetTableUsage.MAPPING_NAME).getResultList();
    }

    public List<GetTableUsageDto> findById(String id) {
        StringBuilder q = new StringBuilder("SELECT * FROM table_usages where id = :id");
        return  entityManager.createNativeQuery(q.toString(), TableUsageMapping.GetTableUsage.MAPPING_NAME).
                setParameter("id", id).getResultList();
    }

    public void create(AddTableUsageDto request) {
        TableUsage tableUsage = new TableUsage();
        tableUsage.tableId = request.table_id;
        tableUsage.capacity = request.capacity;
        tableUsage.duration = request.duration;
        tableUsage.is_active = request.is_active;
        tableUsage.persist();
    }

    public void update(String id, UpdateTableUsageDto request) {
        TableUsage tableUsage = TableUsage.findById(id);
        if (request.table_id != null) tableUsage.tableId = request.table_id;
        if (request.capacity != 0) tableUsage.capacity = request.capacity;
        if (request.duration != 0) tableUsage.duration = request.duration;
        tableUsage.duration = request.duration;
    }

    @Transactional
    public void delete(String id) {
        TableUsage tableUsage = TableUsage.findById(id);
        tableUsage.delete();
    }

    public void useTable(String id, UseTableUsageDto request) {

    }
}
