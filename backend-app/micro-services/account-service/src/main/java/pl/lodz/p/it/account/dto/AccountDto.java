package pl.lodz.p.it.account.dto;

public abstract class AccountDto<T> {

    public AccountDto() {
    }

    ;

    public AccountDto(T entity) {
        fillProperties(entity);
    }

    public void putProperties(T entity) {

    }

    public void patchProperties(T entity) {

    }

    public void fillProperties(T entity) {

    }
}
