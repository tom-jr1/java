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

public class Teste03Cap05 {

    public static void main(String[] args) {
        List<Usuario> usuarios = getUsers();
        Comparator<Usuario> comparator1 = new Comparator<Usuario>() {

            @Override
            public int compare(Usuario o1, Usuario o2) {
                // TODO Auto-generated method stub
                return o1.getNome().compareTo(o2.getNome());
            }
        };
        Collections.sort(usuarios, comparator1);
        usuarios.forEach(i -> System.out.println(i.getNome()));
        separator(1);
        Collections.shuffle(usuarios);

        Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));
        usuarios.forEach(i -> System.out.println(i.getNome()));
        separator(2);

        /*No J8 foi desenvolvido um método default na interface List chamado sort() que recebe um comparator, tirando a necessidade
         * de usar o Collections.sort. Ele recebe o comparator como arg e implica o tipo de acordo com o tipo da lista que fazz call do
         * List.sort
         */

         usuarios.sort(comparator1);
         usuarios.forEach(i -> System.out.println(i.getNome()));
         separator(3);
         Collections.shuffle(usuarios);


         /*A interface Comparator a qual se usa para criar a class anonima que para ordenação da lista
          * tem em si o método abstrato comparing() que é um factory de comparator 
          ele recebe em seu argumento um lambda, que é uma function. Essa function precisa ter informado ou inferido o tipo de list
          e o tipo do key(o tipo da propriedade pela qual a list sera ordenanda).
          */

          Function<Usuario, String> function1 = u -> u.getNome();
          Comparator<Usuario> comparator2 = Comparator.comparing(function1);
          usuarios.sort(comparator2);
          usuarios.forEach(i -> System.out.println(i.getNome()));
          separator(4);

          /*Ao trabalharmos com uma list de string podemos ordena-la com alguns métodos statics do Comparator como por exemplo o
           * Comparator.naturalOrder(), 
           */
          List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");
          
          palavras.sort(Comparator.naturalOrder());
          palavras.forEach(i -> System.out.println(i));
          separator(5);
          Collections.shuffle(usuarios);

          /* Caso tivermos a necessidade de ordenar pelos pontos dos usuers podemos criar um Function que recebe o ponto do usuario
           * bastantdo mudar o type da key da function. Mas como o Function não recebe tipo primitivo, devemos usar o integer
           */

           Function<Usuario, Integer> function2 = u -> u.getPontos();
           usuarios.sort(Comparator.comparing(function2));
          usuarios.forEach(i -> System.out.println(i.getNome() +" - " + i.getPontos()));
          separator(6);

          /*Para evitar o autoboxing gerado, podemos criar uma function que ja infere que o type do Comparable é int primitivo
           * o ToIntFuction que necessitara ter apenas o type da list informado. O método abstrato do Comparator que recebe-o é o
           * Comparator.compareInt()
           */

           ToIntFunction<Usuario> function3 = u -> u.getPontos();
           usuarios.sort(Comparator.comparingInt(function3));
           usuarios.forEach(i -> System.out.println(i.getNome() +" - " + i.getPontos()));
          separator(7); 
    }
}
