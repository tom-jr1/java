package cap_05_ordenando_no_java_8;

import static util.UtilString.separator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;

public class Teste04Cap05 {
    
    public static void main (String[] args) {
        List<Usuario> list = UtilString.getUsers();

        Collections.sort(list,(u1, u2) -> u1.getNome().compareTo(u2.getNome()));
        list.forEach(i -> System.out.println(i.getNome()));
        Collections.shuffle(list);
        separator(0);


        /* podemos usar o sort , método default da interface List, no java 8 ela chamaa collections.sort a partir do seu codeblock
         * enviando a this como lista, que é a representação da propria list
          */

          list.sort((u1,u2) -> u1.getNome().compareTo(u2.getNome()));
          list.forEach(i -> System.out.println(i.getNome()));
          Collections.shuffle(list);
          separator(1);


          /* Agora vamos aprender a usar o método static da Interface Comparator. Ao inves de criar uma class anonima de Comparator 
          usado lambda expression. Iremos usar o method static comparing para nos retorna o comparator que desejamos
          nele passamos como args uma function que extrai da minha lista o key extractior que é a propriedade e seu type que irei
          fazer a ordenação. */

          Function<Usuario, String> function1 =  new Function<Usuario,String>() {

            @Override
            public String apply(Usuario t) {
                // TODO Auto-generated method stub
                return t.getNome();
            }
            
          };
          Comparator<Usuario> comparator2 = Comparator.comparing(function1);
          list.sort(comparator2);
          list.forEach(i -> System.out.println(i.getNome()));
          Collections.shuffle(list);
          separator(2);


          /*Para ordenarmos pelos pontos do usuarios poderimos simplementes na Function informar um type Integer pois int primitivo nao é 
           * aceito. e no método apply informa o propriedade do type int que seria feito um autoboxing de int para Integer. 
           * Mas para evitar autoboxing poderiamos usar a interface IntToFunction<T> que evita fazer autobixing. E no Compator usaremos
           * o static method comparingInt que recebe como arg uma IntToFunction<>
           * 
           * No IntToFunction so tema necessidade de informa o type do objeto da lista ja que ele é proprio para extrair
           * int. E caso não for o type da propriedade informada no apply, ele gera erro de compilação
           */


           ToIntFunction<Usuario> toIntFunc1 =  new ToIntFunction<Usuario>() {

            @Override
            public int applyAsInt(Usuario value) {
                // TODO Auto-generated method stub
                return value.getPontos();
            }
            
           };
           Comparator<Usuario> comparator3 = Comparator.comparingInt(toIntFunc1);
           list.sort(comparator3);
           list.forEach(i -> System.out.println(i.getPontos() + "---" + i.getNome()));
           Collections.shuffle(list);
           separator(3);
    }
}
