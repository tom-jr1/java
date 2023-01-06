package cap_09_grouping_parting;

import static util.UtilString.getUsers;
import static util.UtilString.separator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

public class T01Cap9 {
    public static void main(String[] args) throws IOException {

        Stream<String> s = Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".md"))
                .flatMap(p -> lines(p));
        s.forEach(System.out::println);
        separator(1);

        /* Pegar quantidade de linhas de cada arquivo */
        LongStream lines = Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".md"))
                .mapToLong(p -> lines(p).count());
        lines.forEach(System.out::println);
        separator(2);

        /* coletar o result para um map */
        Map<Path, Long> map1 = Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".md"))
                .collect(Collectors.toMap(p -> p, p -> lines(p).count()));
        System.out.println(map1);
        separator(3);

        /* mapeando users com nome como chave */
        Map<String, Usuario> map2 = getUsers().stream()
                .collect(Collectors.toMap(Usuario::getNome, Function.identity()));
        System.out.println(map2);

        /* Agrupando Users por pontuaçao */

        Map<Integer, List<Usuario>> map3 = getUsers().stream().collect(Collectors.groupingBy(Usuario::getPontos));
        System.out.println(map3);
        separator(4);

        /* particionando Usuarios por um predicate */

        Map<Boolean, List<Usuario>> map4 = getUsers().stream().collect(Collectors.partitioningBy(Usuario::isModerador));
        System.out.println(map4);
        separator(5);

        /* particionando Usuarios por um predicate e coletando apenas os nomes */

        Map<Boolean, List<String>> map5 = getUsers().stream()
                .collect(Collectors.partitioningBy(Usuario::isModerador,
                        Collectors.mapping(Usuario::getNome, Collectors.toList())));

        System.out.println(map5);

        /* coletando a soma dos pontos no partition */

        Map<Boolean, Integer> map6 = getUsers().stream().collect(Collectors.partitioningBy(Usuario::isModerador, 
        Collectors.summingInt(Usuario::getPontos)));
        System.out.println(map6);
    }

    public static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            // TODO: handle exception
            throw new UncheckedIOException(e);
        }
    }
}
