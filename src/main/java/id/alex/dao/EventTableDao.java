package id.alex.dao;

import id.alex.dto.eventtable.*;
import id.alex.enums.TableStatus;
import id.alex.helpers.UtilsHelper;
import id.alex.models.EventTable;
import id.alex.models.mapping.EventTableMapping;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EventTableDao {
    @Inject
    EntityManager entityManager;
    public List<GetEventTableDto> getAll(RequestParamEventTableDto request) {
        StringBuilder q = new StringBuilder("SELECT * FROM event_tables ");
        q.append("WHERE 1=1 ");
        if (!UtilsHelper.isNullOrEmpty(request.name)) {
            q.append("AND lower(name) LIKE '%").append(request.name.toLowerCase()).append("%' ");
        }

        if (!UtilsHelper.isNullOrEmpty(request.outlet_id)) {
            q.append("AND lower(outlet_id) LIKE '%").append(request.outlet_id.toLowerCase()).append("%' ");
        }

        if (!UtilsHelper.isNullOrEmpty(request.status.toString())) {
            q.append("AND lower(status) LIKE '%").append(request.status.toString().toLowerCase()).append("%' ");
        }

        return entityManager.createNativeQuery(q.toString(), EventTableMapping.GetEventTable.MAPPING_NAME).getResultList();
    }

    public EventTableMapping.GetEventTable findById(String id) {
        StringBuilder q = new StringBuilder("SELECT * FROM event_tables where id = :id");
        return (EventTableMapping.GetEventTable) entityManager.createNativeQuery(q.toString(), EventTableMapping.GetEventTable.MAPPING_NAME).
                setParameter("id", id)
                .getSingleResult();
    }

    public void create(AddEventTableDto request) {
        EventTable eventTable = new EventTable();
        eventTable.outletId = request.outlet_id;
        eventTable.name = request.name;
        eventTable.max_capacity = request.max_capacity;
        eventTable.tableStatus = TableStatus.available;
        eventTable.persist();
    }

    public void update(String id, UpdateEventTableDto request) {
        EventTable eventTable = EventTable.findById(id);
        if (request.name != null) eventTable.name = request.name;
        if (request.max_capacity != null) eventTable.max_capacity = request.max_capacity;
        if (request.total_usage != null) eventTable.total_usage = request.total_usage;
        if (request.status != null) eventTable.tableStatus = request.status;
        if (request.total_duration != null) eventTable.total_duration = request.total_duration;
        if (request.usage_capacity != null) eventTable.usage_capacity = request.usage_capacity;
    }

    @Transactional
    public void delete(String id) {
        EventTable eventTable = EventTable.findById(id);
        eventTable.delete();
    }

    public void changeStatus(String id, ChangeStatusEventTableDto request) {
        EventTable eventTable = EventTable.findById(id);
        if (request.status != null) eventTable.tableStatus = request.status;
    }
}
