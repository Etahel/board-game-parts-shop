package pl.lodz.p.it.bges.core.definitions;

public interface Dto<T> {
    void putProperties(T entity);

    void patchProperties(T entity);

    void fillProperties(T entity);
}
