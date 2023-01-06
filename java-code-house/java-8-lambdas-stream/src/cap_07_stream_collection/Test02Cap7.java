package cap_07_stream_collection;

import static util.UtilString.separator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;

public class Test02Cap7 {
    public static void main(String[] args) {
        List<Usuario> l1 = UtilString.getUsers();
        /*
         * O Stream tem o objetivo de armazenar os novos métodos collections. Assim
         * quando quisermos usar o método filter por exemplo
         * nos usamos o method default stream da collection para retornar um stream de
         * mesmo type da nossacollection. Assim podemos realizar
         * varias operações em pipeline com a collection. As alterações não alteram a
         * List original apenas retorna uma stream com as alterações
         * realizadas no pipe
         */

        /* Vamos usar o filter do stream para filtrar usuarios com mais de 130 pontos */

        Stream<Usuario> s1 = l1.stream().filter((u) -> u.getPontos() > 130);
        // eu poderia chamar esse método forEach no pipeline porem nao haveria retorno
        // de uma stream pois o mesmo é void
        // ficaria:
        // l1.stream().filter((u) -> u.getPontos() > 130).forEach(System.out::println);
        s1.forEach(System.out::println);
        separator(1);

        List<Usuario> l2 = l1.stream().filter(u -> u.getPontos() > 130).collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll);
        l2.forEach(System.out::println);
        separator(2);

        List<Usuario> l3 = l1.stream().filter(u -> u.getPontos() > 130).collect(Collectors.toList());
        l3.forEach(System.out::println);
        separator(3);

        /*
         * Existe um method de Collectors que se chama toSet assim ele devolve o result
         * do pipeline como um set
         * podemos tbm usar o toCollection que nos da a liberdade de falar qual
         * contrutor de qual classe sera usada para o retorno
         */

        Set<Usuario> set1 = l1.stream().filter(u -> u.getPontos() > 130).collect(Collectors.toSet());
        set1.forEach(System.out::println);
        separator(4);

        l1.stream().collect(Collectors.toCollection(ArrayList::new)); // HashSet::new || LinkedHashSet::new ...

        /*
         * Se quisermos remover da stream apenas a lista de alguma propriedade podemos
         * utilizar o Map um método que mapeia.
         * Segue um exemplo do mepeamento dos pontos de um user para uma lista de
         * Integer
         */

        List<Integer> li1 = l1.stream().map(Usuario::getPontos).collect(Collectors.toList());
        li1.forEach(System.out::println);
        separator(5);
        /*
         * List não aceita tipo primitivo. Porem o Stream tem Interface que comportão
         * tais. Como o IntStream,
         */

        /*
         * Vamos pegar a media de pontuação dos users com o mapToInt e average e get as
         * double para nos resultar um type dooble
         * average é uma operação do tipo reducao
         */
        double media = l1.stream().mapToInt(Usuario::getPontos).average().getAsDouble();
        System.out.println(media);
        separator(6);

        /* O average devolve por default um optional double. */
        OptionalDouble optionalDouble = l1.stream().mapToInt(Usuario::getPontos).average();
        media = optionalDouble.orElse(0.0);

        /* Inline */
        media = l1.stream().mapToInt(Usuario::getPontos).average().orElse(0.0);

        /*Captura Usuario de maior ponto */

        Usuario u1 = l1.stream().max(Comparator.comparing(Usuario::getPontos)).orElse(null);
        System.out.println(u1);
        separator(7);

        /*Nome do user com maior ponto */
        String nome = l1.stream().max(Comparator.comparing(Usuario::getPontos)).map(Usuario::getNome).orElse("");
        System.out.println(nome);
        separator(8);
        /*Pontuacao do usuario com mair ponto */
        Integer i = l1.stream().max(Comparator.comparing(Usuario::getPontos)).map(Usuario::getPontos).orElse(0);
        System.out.println(i);

        l1.forEach(System.out::println);
        l1.stream().forEach(Usuario::tornarModerador);
        l1.forEach(System.out::println);
        separator(9);

        /* Caso modificarmos uma lista a partir de um forEach chamado com stream a alteração sera aplicada para a list */
    }
}
