package id.alex.dao;

import id.alex.dto.company.RequestCompanyDto;
import id.alex.dto.company.GetCompanyDto;
import id.alex.models.Company;
import id.alex.models.mapping.CompanyMapping;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CompanyDao {

    @Inject
    EntityManager entityManager;

    public List<GetCompanyDto> getAll() {
        String q = "SELECT * FROM companies";
        return entityManager.createNativeQuery(q, CompanyMapping.GetCompany.MAPPING_NAME).getResultList();
    }

    public CompanyMapping.GetCompany findById(String id) {
        StringBuilder q = new StringBuilder("SELECT * FROM companies where id = :id ");
        return (CompanyMapping.GetCompany) entityManager.createNativeQuery(q.toString(), CompanyMapping.GetCompany.MAPPING_NAME).
                setParameter("id", id).getSingleResult();
    }

    public void create(RequestCompanyDto request) {
        Company company = new Company();
        company.name = request.name;
        company.address = request.address;
        company.tlp = request.tlp;
        company.persist();
    }

    public void updateCompany(String id, RequestCompanyDto request) {
        Company company = Company.findById(id);
        company.name = request.name;
        if (request.address != null) company.address = request.address;
        if (request.tlp != null) company.tlp = request.tlp;
    }

    @Transactional
    public void delete(String id) {
        Company company = Company.findById(id);
        company.delete();
    }

}
