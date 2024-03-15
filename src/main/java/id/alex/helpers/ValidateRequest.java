package id.alex.helpers;

import id.alex.handlers.ValidationHandlerException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;

@ApplicationScoped
public class ValidateRequest {
    @Inject
    Validator validator;

    public void validate(Object o){
        Set<? extends ConstraintViolation> validation = validator.validate(o);
        if(CollectionUtils.isEmpty(validation)){
            return;
        }

        throw new ValidationHandlerException(validation.stream().findFirst().get().getMessage());
    }
}
