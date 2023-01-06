package cap_06_method_reference;

@FunctionalInterface
public interface TriFunction<T, U, X, R> {

    R apply(T t, U u, X X);

}
