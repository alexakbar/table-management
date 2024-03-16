package id.alex.services;

import id.alex.dao.CompanyDao;
import id.alex.dto.company.RequestCompanyDto;
import id.alex.dto.company.GetCompanyDto;
import id.alex.dto.company.RequestParamCompanyDto;
import id.alex.handlers.ValidationHandlerException;
import id.alex.helpers.ValidateRequest;
import id.alex.models.mapping.CompanyMapping;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CompanyService {

    @Inject
    ValidateRequest validateRequest;

    @Inject
    CompanyDao companyDao;

    public List<GetCompanyDto> getAll(RequestParamCompanyDto request) {
        return  companyDao.getAll(request);
    }

    public CompanyMapping.GetCompany findById(String Id){
        return companyDao.findById(Id);
    }

    public void create(RequestCompanyDto request) throws ValidationHandlerException {
        validateRequest.validate(request);
        companyDao.create(request);
    }

    public void updateCompany(String id, RequestCompanyDto request) throws ValidationHandlerException {
        validateRequest.validate(request);
        companyDao.updateCompany(id,request);
    }

    public void delete(String id) {
        companyDao.delete(id);
    }
}
