package pl.lodz.p.it.bges.account.dto;

import pl.lodz.p.it.bges.core.definitions.Dto;

public abstract class AccountDto<T> implements Dto<T> {

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
