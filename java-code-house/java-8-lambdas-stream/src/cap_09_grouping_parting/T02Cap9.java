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
import java.util.stream.Stream;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

public class T02Cap9 {
    public static void main(String[] args) throws IOException {

        Map<Path, Long> map1 = Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".md"))
                .collect(Collectors.toMap(Function.identity(), p -> lines(p).count()));
        System.out.println(map1);

        Map<String, Usuario> map2 = getUsers().stream()
                .collect(Collectors.toMap(Usuario::getNome, Function.identity()));
        System.out.println(map2);

        Map<Integer, List<Usuario>> map3 = getUsers().stream().collect(Collectors.groupingBy(Usuario::getPontos));
        System.out.println(map3);
        separator(1);
        Map<Boolean, List<String>> map4 = getUsers().stream().collect(
                // Collectors.partitioningBy(Usuario::isModerador));
                Collectors.partitioningBy(Usuario::isModerador,
                        Collectors.mapping(Usuario::getNome, Collectors.toList())));
        map4.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
            System.out.println("***");
        });
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
