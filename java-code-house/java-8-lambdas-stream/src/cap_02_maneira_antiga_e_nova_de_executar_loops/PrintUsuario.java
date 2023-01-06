package cap_02_maneira_antiga_e_nova_de_executar_loops;

public class PrintUsuario implements java.util.function.Consumer<Usuario> {

	@Override
	public void accept(Usuario t) {
		// TODO Auto-generated method stub
		System.out.println(t.getNome());
	}

}
