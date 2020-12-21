package pl.lodz.p.it.bges.inventory.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
import pl.lodz.p.it.bges.inventory.exception.element.ElementNotFoundException;
import pl.lodz.p.it.bges.inventory.exception.element.NegativeStockException;

import java.util.List;

@ControllerAdvice
public class InventoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NegativeStockException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorDto handleConflict(InventoryException ex) {
        return new ErrorDto(ex);
    }

    //
//    @ExceptionHandler(value
//            = {})
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected ErrorDto handleInternalError(InventoryException ex) {
//        return new ErrorDto(ex);
//    }
//
//    @ExceptionHandler(value
//            = {})
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ErrorDto handleBadRequest(InventoryException ex) {
//        return new ErrorDto(ex);
//    }
//
    @ExceptionHandler(value
            = {ElementNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorDto handleNotFound(InventoryException ex) {
        return new ErrorDto(ex);
    }


    // Default spring validation exception
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

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorDto handleAnyException() {
        return new ErrorDto(AppError.SERVER_ERROR.toString());
    }
}

