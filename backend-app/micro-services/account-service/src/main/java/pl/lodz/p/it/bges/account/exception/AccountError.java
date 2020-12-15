package pl.lodz.p.it.bges.account.exception;

public enum AccountError {

    //KEYCLOAK ERRORS
    KEYCLOAK_CONNECTION_FAILURE("keycloak.connection_failure"),
    VERIFICATION_EMAIL_NOT_SENT("keycloak.verification.email.not_send"),
    //USER ERRORS
    USERNAME_TAKEN("user.username_taken"),
    EMAIL_TAKEN("user.email_taken"),
    EMAIL_ALREADY_VERIFIED("user.email_already_verified"),
    REGISTRATION_INVALID_DATA("user.registration.invalid_data"),
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
