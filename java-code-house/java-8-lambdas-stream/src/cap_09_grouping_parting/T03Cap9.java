package cap_09_grouping_parting;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

import static util.UtilString.*;

public class T03Cap9 {

    public static void main(String[] args) throws IOException {
        Map<Path, Long> map1 = Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".md"))
                .collect(Collectors.toMap(Function.identity(), p -> lines(p).count()));

        map1.forEach((key, value) -> System.out.println(key + " = " + value));
        separator(1);

        Map<Path, List<String>> map2 = Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".md"))
                .collect(Collectors.toMap(Function.identity(), p -> lines(p).collect(Collectors.toList())));

        map2.forEach((key, value) -> System.out.println(key + " = " + value));
        separator(2);

        Map<Integer, List<Usuario>> map3 = getUsers().stream().collect(Collectors.groupingBy(Usuario::getPontos));
        map3.forEach((key, value) -> System.out.println(key + " = " + value));
        separator(3);

        Map<Boolean, List<Usuario>> map4 = getUsers().stream().collect(Collectors.partitioningBy(Usuario::isModerador));
        map4.forEach((key, value) -> System.out.println(key + " = " + value));
        separator(4);

        Map<Boolean, List<String>> map5 = getUsers().stream().collect(Collectors.partitioningBy(Usuario::isModerador,
                Collectors.mapping(Usuario::getNome, Collectors.toList())));
                map5.forEach((key, value) -> System.out.println(key + " = " + value));
        separator(5);


        Map<Boolean, Integer> map6 = getUsers().stream().collect(Collectors.partitioningBy(Usuario::isModerador,
        Collectors.summingInt(Usuario::getPontos)));

        map6.forEach((key, value) -> System.out.println(key + " = " + value));
        separator(6);

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
