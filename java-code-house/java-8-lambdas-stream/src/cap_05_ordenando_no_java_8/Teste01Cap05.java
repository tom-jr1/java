package cap_05_ordenando_no_java_8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import static util.UtilString.separator;
import static util.UtilString.getUsers;

public class Teste01Cap05 {
    public static void main(String[] args) {
        /*
         * Por enquanto. Nos criamos usamos o Collections.list(list, comparator) para
         * que o segundo arg, um coparator
         * organize a ordem da list
         */

        List<Usuario> usuarios = getUsers();

        Comparator<Usuario> comparator = new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        };

        usuarios.forEach(u -> System.out.println(u.getNome()));
        separator(1);

        Collections.sort(usuarios, comparator);
        Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));

        usuarios.forEach(u -> System.out.println(u.getNome()));
        separator(2);

        /*
         * Intessante citar os métodos statics de ordenação.
         * String.CASE_INSENSITIVE_ORDER
         * o nome ja revela sobre si.
         */
        Collections.sort(usuarios, (u1, u2) -> String.CASE_INSENSITIVE_ORDER.compare(u1.getNome(), u2.getNome()));

        /*
         * A um novo método default na interface List, o sort. Ele recebe o comparator e
         * chama em seu bloco o Collections.list
         */
        usuarios.sort(comparator);

        /*
         * Com a possibilidade de existir métodos staticos nas interfaces. Podemos usar
         * o método comparing da class Comparator
         * um factory de comparator ele retorna o comparator necessario para a ordenação
         */

        usuarios.sort(Comparator.comparing(u1 -> u1.getNome()));

        /* Indexando por ordem natural */
        List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");
        palavras.forEach(item -> System.out.println(item));
        separator(3);

        palavras.sort(Comparator.comparing(item -> item));

        palavras.forEach(item -> System.out.println(item));
        separator(4);

        /*
         * No java 8 indica que não era possivel fazer da forma feita. Aparentemente no
         * Java 17 funciona
         * Mas para conhecimento para quando estiver usando o Java8. Poderiamos usar o
         * método Comparator.naturalOrder() para
         * criar o comparator para essa situação
         */

        palavras.sort(Comparator.naturalOrder());

        /*
         * Fragmentando o Comparator.compare
         * No caso o comparing recebe uma function que recebe no dimont dois tipos. Em
         * nosso exemplo os tipos são o objeto
         * no qual a list é tipo e segundo type é o type da propriedade pela qual será
         * feito o sort. ele é alimentado com um lambda
         */

        Function<Usuario, String> function = u -> u.getNome();
        usuarios.sort(Comparator.comparing(function));

        /*Function não aceita em seus types tipo primitivo. No caso para pegar o getPontos faz-se um boxing para Integer */
        Function<Usuario, Integer> function2 = u -> u.getPontos();
        usuarios.sort(Comparator.comparing(function2));
        usuarios.forEach(item -> System.out.println(item.getPontos()));
        separator(5);

        /*Para evitar autoBoxing podemos usar uma interface para cada tipo primitivo
         * A Interface ToIntFunction ja tem implicita seu segundo type, sendo necessario apenas o Type da list
         * e o mét
	public static void separator() {
		System.out.println("****");
	}odo statico do Comparator que recebe esse Tipo ToIntFunction é o comparaingInt
         */
        ToIntFunction<Usuario> function3 = Usuario::getPontos;
        usuarios.sort(Comparator.comparingInt(function3));
        usuarios.forEach(item -> System.out.println(item.getPontos()));
        separator(6);
    }
}
