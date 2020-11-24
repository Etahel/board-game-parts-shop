package pl.lodz.p.it.account.dto;

public abstract class AbstractDto<T> {

    public AbstractDto() {
    }

    ;

    public AbstractDto(T entity) {
        fillProperties(entity);
    }

    public void putProperties(T entity) {

    }

    public void fillProperties(T entity) {

    }
}
