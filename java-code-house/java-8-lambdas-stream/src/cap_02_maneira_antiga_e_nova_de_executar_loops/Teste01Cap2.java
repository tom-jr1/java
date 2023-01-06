package cap_02_maneira_antiga_e_nova_de_executar_loops;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import util.UtilString;

public class Teste01Cap2 {
	public static void main(String[] args) {

		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getNome());
		}
		UtilString.separator(4);
		// forEach(). Recebe um argumento java.util.function.Consumer com o unico
		// método, accept
		// neste exemplo Criamos uma class que implementa java.util.function.Consumer e
		// implementamos o seu método
		// accept. Então passamos uma instancia desse objeto como argumento do forEach
		PrintUsuario print = new PrintUsuario();
		usuarios.forEach(print);
		UtilString.separator(5);

//		Podemos abstrair a criação da class e simplesmente a fazer de forma anonima, segue o exemplo
		Consumer<Usuario> print2 = new Consumer<Usuario>() {

			@Override
			public void accept(Usuario t) {
				// TODO Auto-generated method stub
				System.out.println(t.getNome());
			}
		};

		usuarios.forEach(print2);
		UtilString.separator(1);

//		Podemos abstrair ainda mais essa função. Evitamos a criação de uma instancia do consumer e declaramos um new
//		diretamento no argumento do forEach

		usuarios.forEach(new Consumer<Usuario>() {
			@Override
			public void accept(Usuario t) {
				// TODO Auto-generated method stub
				System.out.println(t.getNome());
			}
		});
		UtilString.separator(2);

//		trabalhando com lambdas
		// Simplificando. Lambdas é uma forma de simplificar a implementação de uma
		// interface funcional. Interfaces com
//		apenas um método. No qual o java.util.function.Consumer é candidato a ser expresso em lambda
//vamos fazer expressar em lambdas a instanciacao da interface consumer
		Consumer<Usuario> printSemLambda = new Consumer<Usuario>() {

			@Override
			public void accept(Usuario t) {
				// TODO Auto-generated method stub
				System.out.println(t.getNome());
			}
		};

		usuarios.forEach(printSemLambda);
		Consumer<Usuario> printComLambda = (Usuario u) -> {
			System.out.println(u.getNome());
		};
		usuarios.forEach(printComLambda);
		
//		O trecho (Usuario u) é passado com argumento para o unico método da interface pelo compilador
//		O compilador ainda consegue fazer essa inferencia caso abstrairmos os parenteses e o tipo
		
		Consumer<Usuario> printComLambdaV2 = u -> {System.out.println(u);};
		usuarios.forEach(printComLambdaV2);
		
//		mais uma bastração possivel é remover as chaves ja que não possui mais de uma instrunção no corpo
		Consumer<Usuario> printComLambdaV3 = u -> System.out.println(u);
		usuarios.forEach(printComLambdaV3);
		
//		podemos tambem passar esse codigo diretamente para o foreach, facilitando ainda mais 
		usuarios.forEach(u-> System.out.println(u.getNome()));
		UtilString.separator(2);
		
		// vamos percorrer com forEach e tornar todos usuarios moderadores
		usuarios.forEach(u -> u.tornarModerador());
		
	}
}
