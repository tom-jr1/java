package cap_08_mais_op_streams;

import static util.UtilString.getUsers;
import static util.UtilString.separator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

public class P1Cap08 {
    public static void main(String[] args) throws IOException {

        /*
         * Para fazer ordenação no stream usamos o method sorted. Ele usa os mesmos args
         * que sort de list e Collections. E tem o lembrete que operações de pipe não
         * alteram
         * a list original, sendo assim usado collect quando quisermos um retorno de um
         * type
         * collection especifico. Segue um exemplo de sort by name apos filtrar os users
         * com
         * pontos maior que 130
         */
        getUsers().stream().filter(u -> u.getPontos() > 130).sorted(Comparator.comparing(Usuario::getPontos))
                .forEach(System.out::println);
        separator(1);

        /*
         * Revendo o méthod max e o sum
         * retornar user com maior ponto
         * retorna a soma de todos pontos
         */
        System.out.println(getUsers().stream().max(Comparator.comparing(Usuario::getPontos)).get());
        System.out.println(getUsers().stream().mapToInt(Usuario::getPontos).sum());
        separator(2);

        /*
         * o max é uma operação de redução. pomos fazer a operação de redução que
         * realiza o mesmo que
         * o max. Ele vai precisar de dois args int. então implementamos as somas, todo
         * elemento se soma ao proximo
         * e o valor vai para o identity que é o acumulador
         */

        System.out.println(getUsers().stream().mapToInt(Usuario::getPontos)
                .reduce(0, (int i1, int i2) -> i1 + i2));
        separator(3);

        /*
         * Poderiamos usar o Method Reference com o sum do Integer. os args seriam
         * inferido e a implementação
         * não seria necessario pos ja existe na sum
         * se entrar no Integer vera o method com o mesmo retorno que adicionamos no
         * lambda
         */

        System.out.println(getUsers().stream()
                .mapToInt(Usuario::getPontos).reduce(0, Integer::sum));

        /* Multiplicar todos os Pontos com reduce */
        System.out.println(getUsers().stream().mapToDouble(Usuario::getPontos)
                .reduce(1, (y, z) -> y * z));
        separator(4);

        /*
         * Este exemplo é usado quando queremos evitar o uso do map. Pois pode ser
         * custoso em algumas operações
         */
        System.out.println(getUsers().stream()
                .reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum));
        separator(5);

        /* method anyMatches */

        System.out.println(getUsers().stream().anyMatch(u -> u.getPontos() > 170));

        Random r = new Random(0);
        // List<Integer> ints =
        IntStream.generate(() -> r.nextInt())
                .limit(100).boxed().collect(Collectors.toList());

        separator(6);

        /* Listar todos arquivos de um diretorio */

        Files.list(Paths.get("src/cap_08_mais_op_streams")).forEach(System.out::println);
        separator(7);

        /* Pegar arquivos que terminam com '.java' */
        Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(System.out::println);

        Files.list(Paths.get("src/cap_08_mais_op_streams"))
                .filter(p -> p.toString().endsWith(".java"))
                .flatMap(p -> {
                    try {
                        return Files.lines(p);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }).forEach(System.out::println);

    }
}
