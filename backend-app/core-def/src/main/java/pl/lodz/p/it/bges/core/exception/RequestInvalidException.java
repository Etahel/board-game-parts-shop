package pl.lodz.p.it.bges.core.exception;


public class RequestInvalidException extends AppException {

    public RequestInvalidException() {
        super("validation.not_valid");
    }
}
