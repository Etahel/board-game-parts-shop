package pl.lodz.p.it.account.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.it.account.dto.ErrorDto;
import pl.lodz.p.it.account.exception.AccountException;
import pl.lodz.p.it.account.exception.keycloak.KeycloakConnectionException;
import pl.lodz.p.it.account.exception.user.EmailTakenException;
import pl.lodz.p.it.account.exception.user.RegistrationDataException;
import pl.lodz.p.it.account.exception.user.UsernameTakenException;

@ControllerAdvice
public class AccountResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            UsernameTakenException.class,
            EmailTakenException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorDto handleConflict(AccountException ex) {
        return new ErrorDto(ex);
    }

    @ExceptionHandler(value
            = {KeycloakConnectionException.class})
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
}
