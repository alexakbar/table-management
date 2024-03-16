package id.alex.dao;

import id.alex.dto.company.GetCompanyDto;
import id.alex.dto.company.RequestCompanyDto;
import id.alex.dto.outlet.AddOutletDto;
import id.alex.dto.outlet.GetOutletDto;
import id.alex.dto.outlet.RequestOutletDto;
import id.alex.models.Company;
import id.alex.models.Outlet;
import id.alex.models.mapping.CompanyMapping;
import id.alex.models.mapping.OutletMapping;
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
}
