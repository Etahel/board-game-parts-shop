package pl.lodz.p.it.bges.core.exception;

public enum AppError {

    REQUEST_NOT_VALID("validation.not_valid"),
    VERSION_MISMATCH("version.mismatch"),
    DATABASE_ERROR("database.operation_failed"),
    SERVER_ERROR("server.error");

    private final String code;

    AppError(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
