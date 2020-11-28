package pl.lodz.p.it.account.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.it.account.dto.error.ErrorDto;
import pl.lodz.p.it.account.dto.error.ValidationErrorDto;
import pl.lodz.p.it.account.exception.keycloak.KeycloakConnectionException;
import pl.lodz.p.it.account.exception.keycloak.VerificationEmailException;
import pl.lodz.p.it.account.exception.user.*;
import pl.lodz.p.it.account.exception.validation.RequestInvalidException;

import java.util.List;

@ControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            UsernameTakenException.class,
            EmailTakenException.class,
            EmailAlreadyVerifiedException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorDto handleConflict(AccountException ex) {
        return new ErrorDto(ex);
    }

    @ExceptionHandler(value
            = {KeycloakConnectionException.class, VerificationEmailException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorDto handleInternalError(AccountException ex) {
        return new ErrorDto(ex);
    }

    @ExceptionHandler(value
            = {RegistrationDataException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDto handleBadRequest(AccountException ex) {
        return new ErrorDto(ex);
    }

    @ExceptionHandler(value
            = {UserNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleNotFound(AccountException ex) {
        return new ErrorDto(ex);
    }


    // Default spring validation exception
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity(processFieldErrors(new RequestInvalidException(), fieldErrors), headers, status);
    }

    private ValidationErrorDto processFieldErrors(AccountException ex, List<FieldError> fieldErrors) {
        ValidationErrorDto error = new ValidationErrorDto(ex);
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            error.addFieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

}
