package entity;

@FunctionalInterface
public interface CustomWrapper<T,Type,R> {
    R wrap(T t,Type type);
}
