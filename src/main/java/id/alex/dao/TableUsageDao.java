package id.alex.dao;

import id.alex.dto.eventtable.AddEventTableDto;
import id.alex.dto.eventtable.GetEventTableDto;
import id.alex.dto.eventtable.UpdateEventTableDto;
import id.alex.dto.tableusage.*;
import id.alex.helpers.UtilsHelper;
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
    public List<GetTableUsageDto> getAll(RequestParamTableUsageDto request) {
        StringBuilder q = new StringBuilder("SELECT * FROM table_usages ");
        q.append("WHERE 1=1 ");
        if (!UtilsHelper.isNullOrEmpty(request.table_id)) {
            q.append("AND lower(table_id) LIKE '%").append(request.table_id.toLowerCase()).append("%' ");
        }

        if (request.is_active) {
            q.append("AND is_active = true ");
        } else {
            q.append("AND is_active = false ");
        }

        return entityManager.createNativeQuery(q.toString(), TableUsageMapping.GetTableUsage.MAPPING_NAME).getResultList();
    }

    public TableUsageMapping.GetTableUsage findById(String id) {
        StringBuilder q = new StringBuilder("SELECT * FROM table_usages where id = :id");
        return  (TableUsageMapping.GetTableUsage) entityManager.createNativeQuery(q.toString(), TableUsageMapping.GetTableUsage.MAPPING_NAME).
                setParameter("id", id).getSingleResult();
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
        if (request.capacity != null) tableUsage.capacity = request.capacity;
        if (request.duration != null) tableUsage.duration = request.duration;
        tableUsage.is_active = request.is_active;
    }

    @Transactional
    public void delete(String id) {
        TableUsage tableUsage = TableUsage.findById(id);
        tableUsage.delete();
    }
}
