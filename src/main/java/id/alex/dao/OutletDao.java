package id.alex.dao;

import id.alex.dto.outlet.AddOutletDto;
import id.alex.dto.outlet.RequestOutletDto;
import id.alex.dto.outlet.report.GetReportTableUsageDto;
import id.alex.models.Outlet;
import id.alex.models.mapping.OutletMapping;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class OutletDao {

    @Inject
    EntityManager entityManager;

    public StringBuilder baseQuery() {
        return new StringBuilder("select o.*, " +
                "e.name table_name, " +
                "e.status table_status, " +
                "e.usage_capacity table_usage_capacity, " +
                "e.max_capacity table_max_capacity " +
                "from outlets o " +
                "left join event_tables e on e.outlet_id = o.id");
    }

    public List<OutletMapping.GetOutlet> getAll() {
        return entityManager.createNativeQuery(baseQuery().toString(),OutletMapping.GetOutlet.MAPPING_NAME).getResultList();
    }

    public List<OutletMapping.GetOutlet> findById(String id) {
        StringBuilder q = baseQuery().append(" where o.id = :id ");
        return  entityManager.createNativeQuery(q.toString(), OutletMapping.GetOutlet.MAPPING_NAME).
                setParameter("id", id).getResultList();
    }

    public void create(AddOutletDto request) {
        Outlet outlet = new Outlet();
        outlet.name = request.name;
        outlet.companyId = request.company_id;
        outlet.persist();
    }

    public void update(String id, RequestOutletDto request) {
        Outlet outlet = Outlet.findById(id);
        outlet.name = request.name;
        if (request.company_id != null) outlet.companyId = request.company_id;
    }

    @Transactional
    public void delete(String id) {
        Outlet outlet = Outlet.findById(id);
        outlet.delete();
    }

    public List<OutletMapping.ReportUsageTable> reportUsage(GetReportTableUsageDto request){
        StringBuilder q = new StringBuilder("SELECT " +
                "o.id, " +
                "o.name, " +
                "o.company_id, " +
                "et.name as table_name, ");

        if (request.start_date != null && request.end_date != null) {
            q.append("coalesce(sum(tu.duration), 0) as total_duration, " +
                    "coalesce(count(tu.id), 0) as total_usage ");
        } else {
            q.append("et.total_duration, " +
                    "et.total_usage ");
        }

        q.append("FROM outlets o " +
                "join event_tables et on et.outlet_id = o.id ");

        q.append("left join table_usages tu on tu.table_id = et.id and is_active = false ");

        if (request.start_date != null && request.end_date != null) {
            q.append("AND DATE(tu.created_at) BETWEEN :start AND :end ");
            q.append("group by o.id, o.name,o.company_id, et.name, et.total_usage, et.total_duration");

           return entityManager.createNativeQuery(q.toString(), OutletMapping.ReportUsageTable.MAPPING_NAME)
                   .setParameter("start", request.start_date)
                   .setParameter("end", request.end_date)
                   .getResultList();
        } else {
            q.append("group by o.id, o.name,o.company_id, et.name, et.total_usage, et.total_duration");
            return entityManager
                    .createNativeQuery(q.toString(), OutletMapping.ReportUsageTable.MAPPING_NAME)
                    .getResultList();
        }
    }
}
