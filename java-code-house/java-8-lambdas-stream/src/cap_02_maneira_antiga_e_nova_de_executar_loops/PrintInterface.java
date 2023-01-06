package cap_02_maneira_antiga_e_nova_de_executar_loops;

@FunctionalInterface
public interface PrintInterface<T>{
    
    public void accept(Usuario t, String s);
}
