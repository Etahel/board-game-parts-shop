package pl.lodz.p.it.account.exception;

public enum AccountError {

    //KEYCLOAK ERRORS
    KEYCLOAK_CONNECTION_FAILURE("keycloak.connection_failure"),
    //USER ERRORS
    USERNAME_TAKEN("user.username_taken"),
    EMAIL_TAKEN("user.email_taken"),
    REGISTRATION_WRONG_DATA("user.registration.wrong_data"),
    USER_NOT_FOUND("user.not_found");

    private final String code;

    AccountError(final String code) {
        this.code = "account." + code;
    }

    @Override
    public String toString() {
        return code;
    }
}
