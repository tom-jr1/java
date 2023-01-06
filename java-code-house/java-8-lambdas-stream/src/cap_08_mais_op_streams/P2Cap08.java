package cap_08_mais_op_streams;

import static util.UtilString.getUsers;
import static util.UtilString.separator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

public class P2Cap08 {
    public static void main(String[] args) throws IOException {

        /* filtro no stream usa o método sorted */
        getUsers().stream().filter(u -> u.getPontos() > 130).sorted(Comparator.comparing(Usuario::getNome))
                .forEach(System.out::println);
        separator(1);
        /*
         * FindyAny: busca qualquer um de acordo com o predicado que é informado em um
         * filter anterior como arg
         */
        System.out.println(getUsers().stream().filter(u -> u.getPontos() > 130)
                .sorted(Comparator.comparing(Usuario::getNome))
                .findAny().orElse(null));
        separator(2);
        /*
         * operações que utilizam os resultados de op stream para retornar um unico
         * result
         * são chamados de operação de redução (avarage, count, max, min e sum).
         * Todos com excesão do sum e count trabalham com optional.
         * O min e max recebem Comparator como argumento.
         * Segue exemplo do max
         */
        System.out.println(getUsers().stream().max(Comparator.comparing(Usuario::getPontos)));
        separator(3);

        /* Somar todos os pontos dos Users */
        System.out.println(getUsers().stream().mapToInt(Usuario::getPontos).sum());
        separator(4);

        /*
         * Usando o reduce para mostrar o sum. Assim termos uma compreesão maior do
         * porcesso de redution
         */
        System.out.println(getUsers().stream().mapToInt(Usuario::getPontos)
                .reduce(0, (x, y) -> Integer.sum(x, y)));
        separator(5);

        /*
         * no lugar do IntBinaryOperator podemos substituir por Integer::sum.
         * que o compile infere a operação de sum
         */
        System.out.println(getUsers().stream().mapToInt(Usuario::getPontos)
                .reduce(0, Integer::sum));
        separator(6);

        /*
         * exemplo da sum sem o map. Em casos o map pode ferir o desempenho sendo
         * necessario realizar operação sem o mesmo
         */

        System.out.println(getUsers().stream()
                .reduce(0, (atual, u) -> atual + u.getPontos(), (x, y) -> Integer.sum(x, y)));
        separator(7);

        /* Utilizando o método iterator do stream */
        getUsers().stream().iterator().forEachRemaining(System.out::println);
        separator(8);

        /*
         * Utilizando anyMatches. Utiliza Predicate como arg
         * tem o allMatches e noneMatches que verificam se todos dão match
         * ou se todos não dão match
         */
        System.out.println(getUsers().stream().anyMatch(Usuario::isModerador));
        System.out.println(getUsers().stream().allMatch(Usuario::isModerador));
        System.out.println(getUsers().stream().noneMatch(Usuario::isModerador));
        separator(9);

        /*
         * gerar uma lista infinita de numeros aleatorios
         * streams infinitas são lazy não executando de cara sua função.
         * Não devemos realizar operações que usa todos os items de uma stream, se isso
         * for feito
         * ficará em loop ate o stackover
         */
        Random random = new Random(0);
        IntStream stream = IntStream.generate(() -> random.nextInt());

        /*
         * Operações de curto circuito são operações que não executam em todos os
         * elementos de uma stream
         * o boxed foi utilizado para retorna um Stream<Integer> assim podendo usar
         * o collect
         */

        List<Integer> integers = stream.limit(4).boxed().collect(Collectors.toList());
        integers.forEach(System.out::println);
        separator(10);

        /* listar todos arquivos de um diretorio */
        Files.list(Paths.get("src/cap_08_mais_op_streams")).forEach(System.out::println);
        separator(11);

        /* Listar apenas arquivos .java */

        Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(System.out::println);
        separator(12);
        /* Todo o conteudo dos arquivos. Usamos o File.lines */
        Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".java"))
                .map(P2Cap08::lines)
                .forEach(s -> s.forEach(System.out::println));
        separator(13);

        /*
         * O result apos de map sera um Stream<Stream<String>>
         * podemos fazer uma forEach dentro do forEach de fato acessar os dados das
         * linhas ou
         * podemos usar o flatMap que desempacota o Stream
         * ex: Stream<Stream<Object>> -> flatMap -> Stream<Object>: assim todas as
         * stream se reunem em uma só.
         */
        Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".md"))
                .flatMap(P2Cap08::lines).forEach(System.out::println);
                separator(14);
    }

    static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            // TODO: handle exception
            throw new UncheckedIOException(e);
        }
    }
}
