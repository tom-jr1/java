package cap_05_ordenando_no_java_8;

import static util.UtilString.getUsers;
import static util.UtilString.separator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

public class Teste02Cap05 {

    public static void main(String[] args) {

        List<Usuario> usuarios = getUsers();

        Collections.sort(usuarios, (u1, u2) -> String.CASE_INSENSITIVE_ORDER.compare(u1.getNome(), u2.getNome()));
        usuarios.forEach(item -> System.out.println(item.getNome()));
        separator(1);

        /*
         * No java 8 foi adicionado method default sort na interface List que chama o
         * sort do Collections. no sort do List
         * não precisa informar o array, ja que ele é passado pela keyword [this]
         */

        usuarios.sort((o1, o2) -> o1.getNome().compareTo(o2.getNome()));
        usuarios.forEach(i -> System.out.println(i.getNome()));
        separator(2);

        /*
         * a interface Comparator tem métodos staticos. Vamos falar sobre o comparing
         * que é um factory de Comparator
         * retorna um comparator de acordo com a propriedade passada como arg do
         * comparing
         */

        Function<Usuario, String> function1 = u -> u.getNome();
        Comparator<Usuario> comparator1 = Comparator.comparing(function1);

        usuarios.sort(comparator1);
        usuarios.forEach(i -> System.out.println(i.getNome()));
        separator(3);

        /*
         * Tudo podendo ser resumido como:{
         * usuarios.sort(comparing(u -> u.getNome()));
         * }
         */

        List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");

        palavras.sort(Comparator.comparing(i -> i));
        palavras.forEach(i -> System.out.println(i));
        separator(4);

        /* Ordenando Usuarios por pontos */

        Function<Usuario, Integer> function2 = u -> u.getPontos();
        usuarios.sort(Comparator.comparing(function2));
        usuarios.forEach(i -> System.out.println(i.getPontos()));
        separator(5);

        /*
         * Percebemos que a function não recebe o type int que é o correspondende ao da
         * class Usuario
         * caso queiramos evitar esse autobxing podemos usar a Interface ToIntFunction
         * que recebe a penas o Usuario no dimont
         * e pega automaticamente o retorno como um int. O Comparator.comparing deve ser
         * substituido por comparingInt que aguarda uma
         * ToIntFunction
         */
        ToIntFunction<Usuario> function3 = u -> u.getPontos();
        usuarios.sort(Comparator.comparingInt(function3));
        usuarios.forEach(u -> System.out.println(u.getPontos()));
        separator(6);
    }

}
