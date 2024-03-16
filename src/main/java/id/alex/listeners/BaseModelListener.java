package id.alex.listeners;

import id.alex.models.BaseModel;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class BaseModelListener {
    @PrePersist
    public void prePersist(BaseModel entity) {
        entity.setCreatedAt(new Date());
        entity.setUpdatedAt(new Date());
    }

    @PreUpdate
    public void preUpdate(BaseModel entity) {
        entity.setUpdatedAt(new Date());
    }

}
