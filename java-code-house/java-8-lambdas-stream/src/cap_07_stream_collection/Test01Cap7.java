package cap_07_stream_collection;

import static util.UtilString.separator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;

public class Test01Cap7 {
    public static void main(String[] args) {
        List<Usuario> list = UtilString.getUsers();

        list.stream().filter(i -> i.getPontos() > 130).forEach(System.out::println);
        separator(1);
        /*
         * Streams methods não alteram stream original. methods streams sempre retornam
         * novas streams
         */

        Stream<Usuario> stream = list.stream().filter(i -> i.getPontos() > 130);
        stream.forEach(System.out::println);
        separator(2);


        /*Operação para filtrar users com mais de 100 pontos */
        list.stream().filter(u -> u.getPontos( ) > 100);// filter recebe um IF predicate que tem seu metodo test(expressionBoolena)


        /*Colentando dados de um filter de forma bruta */
        List<Usuario> filter = new ArrayList<>();

        list.stream().filter(u -> u.getPontos() > 130).forEach(filter::add);
        filter.forEach(System.out::println);
        separator(3);

        /*recuperando resultados da stream como List utilizando o method collect  Function extrai/retorna 
        BiConsume: executa um Consumer com dois paramentros em seu accept*/

        Supplier<ArrayList<Usuario>>  suplier = new Supplier<ArrayList<Usuario>>() {

            @Override
            public ArrayList<Usuario> get() {
                // TODO Auto-generated method stub
                return new ArrayList<>();
            }
            
        }; 

        BiConsumer<ArrayList<Usuario>, Usuario> acumulate = new BiConsumer<ArrayList<Usuario>,Usuario>() {

            @Override
            public void accept(ArrayList<Usuario> t, Usuario u) {
                // TODO Auto-generated method stub
                t.add(u);
                
            }
            
        };

        BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>> combine =  new BiConsumer<ArrayList<Usuario>,ArrayList<Usuario>>() {

            @Override
            public void accept(ArrayList<Usuario> t, ArrayList<Usuario> u) {
                // TODO Auto-generated method stub
                t.addAll(u);
            }
            
        };
         List<Usuario> list2 = list.stream().collect(suplier, acumulate, combine);
         list2.forEach(System.out::println);
         separator(4);


         /* O collect pode receber como um arg uma Interface Collectors que tem varios métodos statics. O qual vamos usar o 
          * to list que ja intermente tem a estruta com o suplier, collector e combine
         */

         List<?> list3 = list.stream().collect(Collectors.toList());
         list3.forEach(System.out::println);
         separator(5);

         /*Filtrar usuarios com mais de 130 pontos e colletar para uma list */
         List<Usuario> l4 = list.stream().filter(u -> u.getPontos() > 130).collect(Collectors.toList());
         l4.forEach(System.out::println);
         separator(6);

         
    }
}
