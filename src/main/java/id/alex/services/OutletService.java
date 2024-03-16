package id.alex.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.alex.dao.OutletDao;
import id.alex.dto.outlet.AddOutletDto;
import id.alex.dto.outlet.GetOutletDto;
import id.alex.dto.outlet.RequestOutletDto;
import id.alex.handlers.ValidationHandlerException;
import id.alex.helpers.ValidateRequest;
import id.alex.models.mapping.OutletMapping;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class OutletService {
    @Inject
    ValidateRequest validateRequest;
    @Inject
    OutletDao outletDao;

    public List<GetOutletDto.Response> getAll()  {
        List<OutletMapping.GetOutlet> response = outletDao.getAll();
        return getResponseList(response);
    }

    private static List<GetOutletDto.Response> getResponseList(List<OutletMapping.GetOutlet> response) {
        List<GetOutletDto.Response> dataResult = new ArrayList<>();
        Map<String, List<OutletMapping.GetOutlet>> outletsById = response.stream()
                .collect(Collectors.groupingBy(OutletMapping.GetOutlet::getId));

        for (Map.Entry<String, List<OutletMapping.GetOutlet>> entry : outletsById.entrySet()) {
            String key = entry.getKey();
            List<OutletMapping.GetOutlet> outlets = entry.getValue();

            GetOutletDto.Response data = new GetOutletDto.Response();
            OutletMapping.GetOutlet outlet = outlets.get(0);
            data.id = outlet.id;
            data.name = outlet.name;
            data.companyId = outlet.company_id;
            data.created_at = outlet.created_at;
            data.updated_at = outlet.updated_at;
            for (OutletMapping.GetOutlet row : outlets){
                if (row.table_name != null){
                    GetOutletDto.TableEvent tableEvent = new GetOutletDto.TableEvent();
                    tableEvent.table_usage_capacity = row.table_usage_capacity;
                    tableEvent.table_status = row.table_status;
                    tableEvent.table_max_capacity = row.table_max_capacity;
                    tableEvent.table_name = row.table_name;
                    tableEvent.created_at = row.created_at;
                    tableEvent.updated_At = row.updated_at;

                    data.tableEvents.add(tableEvent);
                }
            }
            dataResult.add(data);
        }
        return dataResult;
    }

    public List<GetOutletDto.Response> findById(String Id){
        List<OutletMapping.GetOutlet> response = outletDao.findById(Id);
        if (response.isEmpty()) {
            throw new ValidationHandlerException(Response.Status.NOT_FOUND);
        }

        return getResponseList(response);

    }

    public void create(AddOutletDto request) throws ValidationHandlerException {
        validateRequest.validate(request);
        outletDao.create(request);
    }

    public void updateCompany(String id, RequestOutletDto request) {
        outletDao.update(id,request);
    }

    public void delete(String id) {
        outletDao.delete(id);
    }
}
