package id.alex.dto.eventtable;

import id.alex.enums.TableStatus;

public class ChangeStatusEventTableDto {
    public TableStatus status;

    public ChangeStatusEventTableDto(){};
    public ChangeStatusEventTableDto(TableStatus status) {
        this.status = status;
    }
}
