package id.alex.dao;

import id.alex.dto.eventtable.AddEventTableDto;
import id.alex.dto.eventtable.GetEventTableDto;
import id.alex.dto.eventtable.UpdateEventTableDto;
import id.alex.models.EventTable;
import id.alex.models.mapping.EventTableMapping;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EventTableDao {
    @Inject
    EntityManager entityManager;
    public List<GetEventTableDto> getAll() {
        String q = "SELECT * FROM event_tables";
        return entityManager.createNativeQuery(q, EventTableMapping.GetEventTable.MAPPING_NAME).getResultList();
    }

    public List<GetEventTableDto> findById(String id) {
        StringBuilder q = new StringBuilder("SELECT * FROM event_tables where id = :id");
        return  entityManager.createNativeQuery(q.toString(), EventTableMapping.GetEventTable.MAPPING_NAME).
                setParameter("id", id).getResultList();
    }

    public void create(AddEventTableDto request) {
        EventTable eventTable = new EventTable();
        eventTable.outletId = request.outlet_id;
        eventTable.name = request.name;
        eventTable.max_capacity = request.max_capacity;
        eventTable.persist();
    }

    public void update(String id, UpdateEventTableDto request) {
        EventTable eventTable = EventTable.findById(id);
        if (request.name != null) eventTable.name = request.name;
        if (request.max_capacity != 0) eventTable.max_capacity = request.max_capacity;
    }

    @Transactional
    public void delete(String id) {
        EventTable eventTable = EventTable.findById(id);
        eventTable.delete();
    }
}