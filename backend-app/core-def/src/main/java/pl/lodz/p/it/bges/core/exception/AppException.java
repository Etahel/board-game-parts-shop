package pl.lodz.p.it.bges.core.exception;

public class AppException extends Exception {

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
