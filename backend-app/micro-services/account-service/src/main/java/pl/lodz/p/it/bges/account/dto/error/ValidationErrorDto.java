package pl.lodz.p.it.bges.account.dto.error;

import lombok.Getter;
import org.springframework.validation.FieldError;
import pl.lodz.p.it.bges.account.exception.AccountException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorDto extends ErrorDto {

    public ValidationErrorDto(AccountException entity) {
        super(entity);
    }

    private List<FieldError> fieldErrors = new ArrayList<>();

    public void addFieldError(String objectName, String field, String defaultMessage) {
        FieldError error = new FieldError(objectName, field, defaultMessage);
        fieldErrors.add(error);
    }
}
