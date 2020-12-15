package pl.lodz.p.it.bges.core.dto.error;

import lombok.Getter;
import org.springframework.validation.FieldError;
import pl.lodz.p.it.bges.core.exception.AppException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorDto extends ErrorDto {

    public ValidationErrorDto(AppException entity) {
        super(entity);
    }

    private List<FieldError> fieldErrors = new ArrayList<>();

    public void addFieldError(String objectName, String field, String defaultMessage) {
        FieldError error = new FieldError(objectName, field, defaultMessage);
        fieldErrors.add(error);
    }
}
