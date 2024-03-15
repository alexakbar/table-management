package id.alex.services;

import id.alex.dao.OutletDao;
import id.alex.dao.TableUsageDao;
import id.alex.dto.outlet.GetOutletDto;
import id.alex.dto.outlet.RequestOutletDto;
import id.alex.dto.tableusage.AddTableUsageDto;
import id.alex.dto.tableusage.GetTableUsageDto;
import id.alex.dto.tableusage.UpdateTableUsageDto;
import id.alex.dto.tableusage.UseTableUsageDto;
import id.alex.handlers.ValidationHandlerException;
import id.alex.helpers.ValidateRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TableUsageService {
    @Inject
    ValidateRequest validateRequest;
    @Inject
    TableUsageDao tableUsageDao;

    public List<GetTableUsageDto> getAll() {
        return  tableUsageDao.getAll();
    }

    public List<GetTableUsageDto> findById(String Id){
        return tableUsageDao.findById(Id);
    }

    public void create(AddTableUsageDto request) throws ValidationHandlerException {
        validateRequest.validate(request);
        tableUsageDao.create(request);
    }

    public void update(String id, UpdateTableUsageDto request) {
        tableUsageDao.update(id,request);
    }

    public void delete(String id) {
        tableUsageDao.delete(id);
    }

    public void useTable(String id, UseTableUsageDto request) {
        validateRequest.validate(request);

        tableUsageDao.useTable(id, request);

    }
}
