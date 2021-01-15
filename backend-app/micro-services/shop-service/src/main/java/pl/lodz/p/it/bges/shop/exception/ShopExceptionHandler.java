package pl.lodz.p.it.bges.shop.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.it.bges.core.dto.error.ErrorDto;
import pl.lodz.p.it.bges.core.dto.error.ValidationErrorDto;
import pl.lodz.p.it.bges.core.exception.AppError;
import pl.lodz.p.it.bges.core.exception.AppException;
import pl.lodz.p.it.bges.core.exception.RequestInvalidException;
import pl.lodz.p.it.bges.shop.exception.order.*;
import pl.lodz.p.it.bges.shop.exception.stock.StockUnavailableException;

import java.util.List;

@ControllerAdvice
public class ShopExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ShopExceptionHandler.class);

    @ExceptionHandler(value = {ElementChangedException.class, OrderFinalizedException.class, StockUnavailableException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorDto handleConflict(ShopException ex) {
        return new ErrorDto(ex);
    }

    @ExceptionHandler(value
            = {ElementNotFoundException.class, OrderNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleNotFound(ShopException ex) {
        return new ErrorDto(ex);
    }

    @ExceptionHandler(value = OrderAddressIncompleteException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDto handleBadRequest(ShopException ex) {
        return new ErrorDto(ex);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity(processFieldErrors(new RequestInvalidException(), fieldErrors), headers, status);
    }

    private ValidationErrorDto processFieldErrors(AppException ex, List<FieldError> fieldErrors) {
        ValidationErrorDto error = new ValidationErrorDto(ex);
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            error.addFieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

    @ExceptionHandler(value = {OptimisticLockingFailureException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorDto handleOptimisticLockException() {
        return new ErrorDto(AppError.VERSION_MISMATCH.toString());
    }

    @ExceptionHandler(value = {DataAccessException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorDto handleDatabaseException() {
        return new ErrorDto(AppError.DATABASE_ERROR.toString());
    }

    @ExceptionHandler(value = {JsonMappingException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDto handleDeserializationException(Exception e) {
        logger.error("Database exception occured: ", e);
        return new ErrorDto(AppError.DESERIALIZATION_FAILED.toString());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected void handleAccessDenied() {
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorDto handleAnyException(Exception e) {
        logger.error("Server exception occured: ", e);
        return new ErrorDto(AppError.SERVER_ERROR.toString());
    }
}

