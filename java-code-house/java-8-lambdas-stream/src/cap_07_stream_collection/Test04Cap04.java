package cap_07_stream_collection;

import static util.UtilString.separator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;

public class Test04Cap04 {
    public static void main(String[] args) {
        List<Usuario> l1 = UtilString.getUsers();
        /*
         * Ja sabemos que Stream foram criadas para que possamos trabalhar com listas de
         * forma que possamos operar 1 ou varios
         * methods em pipiline sem alterar a lista original e nos retornando de acordo
         * com o ultimo processo do pipe
         * O stream é super modelador podendo ser configurado any operações e tipo de
         * retornos. Iremos ver alguns em seguida
         */
        l1.stream().filter(u -> u.getPontos() > 130).forEach(System.out::println);
        separator(1);

        /*
         * Para retorna uma List de uma stream podemos usar o metodo collect do stream
         * ele necessita de tres
         * args para operar. Um suplier que se comporta como um factory que retorna um
         * novo arraylist a partir
         * do método get do suplier. os outros dois args são BiConsumer(Consumer recebe
         * um arg no seu metodo, bi recebe
         * 2 args em seu method) um guarda referencia ao método de adicao de objeto e o
         * outro a adicao de varios
         * objetos.
         * Eles estao expressos como Method Reference mas poderia ser feito com lambda
         * expression e criando classes anonimas tbm. Criar com classes anonimas da uma
         * noção incrivel de seu funcionamento
         */
        List<Usuario> l2 = l1.stream().filter(u -> u.getPontos() > 130).collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll);
        l2.forEach(System.out::println);
        separator(2);

        /*
         * Mas para poupar tanta implementação anonima nos podemos Usar os methods
         * statics de Collectors
         * o collect aceita esses methods como args dentre varios deles demostraremos o
         * Collectors.toList()
         * que tem em seu interior todo o processo para retornar a stream ja em uma list
         */

        l2 = l1.stream().collect(Collectors.toList());
        System.out.println("l2 é Arraylist?  " + (l2 instanceof ArrayList));
        separator(3);

        /* ha tbm o metodo static toSet que retorna u set */

        Set<Usuario> l3 = l1.stream().collect(Collectors.toSet());
        System.out.println("l3 é HashSet?  " + (l3 instanceof HashSet));
        separator(3);

        /*
         * Outro é o toCollecction que vc define um facotory que retorna o type que vc
         * deseje que retorne por meio
         * deste suplier. Segue exemplo:
         * no arg pode ir qualquer herdeiro de collection. Ja vimos bastente exemplos de
         * Collection
         */

        l3 = l1.stream().collect(Collectors.toCollection(() -> new HashSet<Usuario>()));
        System.out.println("l3 é HashSet?  " + (l3 instanceof HashSet));
        separator(3);

        /* Podemos tbm usar o toArray pra retornamos arrays de primitivos e objetos */
        Usuario[] arr1 = l1.stream().toArray(Usuario[]::new);
        System.out.println(arr1[0]);
        separator(4);

        int[] i = l1.stream().mapToInt(Usuario::getPontos).toArray();
        for (int j : i) {
            System.out.println(j);
        }
        separator(5);

        /*
         * pegar a pontuacao media dos pontos dos usuarios utilizando o map. map é uma
         * forma de mapera uma propriedade
         * do collection. No caso vamos fazer map dos pontos utilizando uma Function que
         * sera um keyExtractor
         * vamos usar o mapToInt para evitar unboxing desnecessario.
         * 
         */

        double media = l1.stream().mapToInt(Usuario::getPontos).average().orElse(0.0);
        System.out.println("media de pontos dos Usuarios: " + media);
        separator(6);

        /*
         * Obter o Ususario com maior valor de pontos com o max que recebe um Comparator
         */
        String u = l1.stream().max(Comparator.comparing(Usuario::getPontos).reversed()).map(Usuario::getNome)
                .orElse("none");
        System.out.println(u);
    }
}
