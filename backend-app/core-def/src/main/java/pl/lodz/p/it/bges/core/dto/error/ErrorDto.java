package pl.lodz.p.it.bges.core.dto.error;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Dto;
import pl.lodz.p.it.bges.core.exception.AppException;

@Getter
@Setter
public class ErrorDto implements Dto<AppException> {

    private String errorMessage;

    public ErrorDto() {
    }

    ;

    public ErrorDto(AppException entity) {
        fillProperties(entity);
    }

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void fillProperties(AppException entity) {
        setErrorMessage(entity.getMessage());
    }

    @Override
    public void putProperties(AppException entity) {

    }

    @Override
    public void patchProperties(AppException entity) {

    }

}
