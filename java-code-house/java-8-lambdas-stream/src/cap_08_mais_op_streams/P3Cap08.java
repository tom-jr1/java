package cap_08_mais_op_streams;

import static util.UtilString.getUsers;
import static util.UtilString.separator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

public class P3Cap08 {
    public static void main(String[] args) throws IOException {
        System.out.println(getUsers().stream().mapToInt(Usuario::getPontos).sum());
        separator(1);

        getUsers().stream().iterator().forEachRemaining(System.out::println);
        separator(2);

        System.out.println("Algum usuario é moderador?");
        System.out.println(getUsers().stream().anyMatch(Usuario::isModerador));
        System.out.println("Todos usuario são moderadores?");
        System.out.println(getUsers().stream().allMatch(Usuario::isModerador));

        System.out.println("Todos usuario não são moderadores?");
        System.out.println(getUsers().stream().noneMatch(Usuario::isModerador));

        separator(3);

        Random random = new Random(0);
        IntStream.generate(() -> random.nextInt()).limit(3).forEach(System.out::println);
        separator(4);

        List<Integer> integers = IntStream.generate(() -> random.nextInt()).limit(3).boxed()
                .collect(Collectors.toList());
        integers.forEach(System.out::println);
        separator(5);

        // listar todos arquivos de um dir
        Files.list(Paths.get("src/cap_08_mais_op_streams")).forEach(System.out::println);
        separator(6);

        // apenas os files com ended .java
        Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".java")).forEach(System.out::println);
        separator(7);
        // imprimir os conteudos dos arquivos
        Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".md")).flatMap(p -> lines(p)).forEach(System.out::println);

    }

    static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException ioException) {
            throw new UncheckedIOException(ioException);
        }
    }
}
