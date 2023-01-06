package cap_03_interface_funcionais;

@FunctionalInterface
public interface Validador<T> {

    boolean validar(T s);

}
