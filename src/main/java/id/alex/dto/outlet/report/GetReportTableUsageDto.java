package id.alex.dto.outlet.report;

import java.util.Date;

public class GetReportTableUsageDto {
    public Date start_date;
    public  Date end_date;

    public GetReportTableUsageDto() {};

    public GetReportTableUsageDto(Date start_date, Date end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
