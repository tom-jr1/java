package cap_02_maneira_antiga_e_nova_de_executar_loops;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static util.UtilString.separator;

public class Teste02Cap2 {

	public static void main(String[] args) {
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		for (Usuario u : usuarios) {
			System.out.println(u.getNome());
		}
		separator(2);

//		forEach() recebe um argumento java.util.function.Consumer com 
//		um unico método (Interface funcional) accept
//		Iremos cria uma class que implemente a interface Consumer
//		para fazer implementar o accept e executar uma acção para cada
//		item do array alunos
		PrintUsuario printUsuario = new PrintUsuario();
		usuarios.forEach(printUsuario);
		separator(1);

//		podemos fazer abstrair a criação de uma class e fazer a 
//		instanciação do objeto que implementa a interface Comsumer
//		a partir de uma class anonima, segue o exemplo
		Consumer<Usuario> print2 = new Consumer<Usuario>() {
			@Override
			public void accept(Usuario t) {
				System.out.println(t.getNome());
			}
		};

		usuarios.forEach(print2);
		separator(1);
//		podemos evoluir maprint2is um nivel na abstração, dando um new na class
//		anonima diretamente com o argumento do forEach
		usuarios.forEach(new Consumer<Usuario>() {
			@Override
			public void accept(Usuario t) {
				// TODO Auto-generated method stub
				System.out.println(t.getNome());
			}
		});
		separator(3);

//		Trabalhando com lamdas
//		Simpificando, Lambdas é uma forma de simplificar a implementacao
//		de uma interface funcional. No caso, o Consumer<> é um candidato
//		devido ser uma IF. Vamos mostrar o Exemplo sem lambda e com lambda

		Consumer<Usuario> nonLambda = new Consumer<Usuario>() {
			@Override
			public void accept(Usuario t) {
				// TODO Auto-generated method stub
				System.out.println(t.getNome());
			}
		};
		usuarios.forEach(nonLambda);
		separator(4);

		// com lambdas
		Consumer<Usuario> lambda1 = (Usuario u) -> {
			System.out.println(u.getNome());
		};
		usuarios.forEach(lambda1);
		separator(5);
//O trecho (Usuario u) é por inferencia passado para como o parametro
//do accpet do método da interface Consumer, e pode ser abstraindo um
//pouco mais: (Usuario u) <=> u

		Consumer<Usuario> lambda2 = u -> {
			System.out.println(u.getNome());
		};
		usuarios.forEach(lambda2);
		separator(6);

//podemos tbm remover as chaves em u-> {}, pois se trata
//apenas de uma linha de comando, sendo assim possivel a remoção da mesma
		Consumer<Usuario> lambda3 = u -> System.out.println(u.getNome());
		usuarios.forEach(lambda3);
		separator(7);

// e por fim, podemos abstrair a criacao da variavel e 
//declarar a expressão direto no arg do forEach
		usuarios.forEach(u -> System.out.println(u.getNome()));
		separator(6);

	}

	public static class PrintUsuario implements Consumer<Usuario> {

		@Override
		public void accept(Usuario t) {
			// TODO Auto-generated method stub
			System.out.println(t.getNome());
		}

	}
}
