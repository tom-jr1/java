package cap_06_method_reference;

import static util.UtilString.separator;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;

public class Teste04Cap06 {
    public static void main(String[] args) {
        /*
         * Reference method é uma forma de expressar a instanciação/implementação de um
         * Interface functional. Ela chega a ser as vezes um
         * modo muito mais abstrato de expressão utilizando muita inferencia e
         * escrevendo pouquissimo codigo. Por exemplo um lambda
         * que expressa um Consumer para chamada de um método para cada iteração de um
         * objeto:
         * (object o) -> o.object.hisMethod();
         * pode ser expresso de tal forma com RM
         * Object::hisMethod;
         */

        /*
         * Pelo que entendi de RM ele pega o type do iterator pela inferencia do
         * List.forEach e o Usuario::tornarModerador é uma referencia
         * a class e o método para executar a cada chamada do method accept do Consumer:
         * Como se o RM falasse 'Para o consumer sera implementado
         * o method tornarModerador da class Usuario'
         */
        List<Usuario> list = UtilString.getUsers();
        Consumer<Usuario> consumer1 = u -> u.tornarModerador();
        consumer1 = Usuario::tornarModerador;
        Consumer<Usuario> print = System.out::println;
        list.forEach(consumer1);
        list.forEach(print);
        separator(1);

        /*
         * Utilizando Method Reference para expressar uma function no
         * Comparaotor.comparing. Em uma function informamos em seu dimont os primeiros
         * types para os paramentros
         * do method apply e o ultimo como o type do retorno do método. No caso da
         * function que segue o retorno sera de um type string
         * no qual sera baseado o sort de list
         */

        Function<Usuario, String> keyExtractor = u -> u.getNome();
        keyExtractor = Usuario::getNome;
        list.sort(Comparator.comparing(keyExtractor));
        list.forEach(print);
        separator(2);

        /*
         * Podemos utilizar o MR para expressar quando quisermos ordenar pela key
         * getPontos que é do tipo int
         * podemos usar o comparingInt que aguarda uma key extrator que retorna com o
         * type int primitivo
         * Vamos ser apresentados tbm para o method themComparing, que é um método
         * default do Comparator
         * que serve para adicionar comparações extras para carater de desempates das
         * demais ordenações ja configuradas
         * tbem temos o Comparator.nullFirst|nullLast que recebe um comparator no qual
         * tera a seus dados null no inicio ou fim da ordenação
         * outro é o reverserd() um method default que configura o comparator para
         * reverter a ordenação configurada
         * then Comparing tem tbm nas versões comparingInt
         */
        list.sort(Comparator
                .nullsLast(Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome).reversed()));
        list.forEach(print);
        separator(3);

        /*
         * A interface runnable não é um iterator nem um Consumer. O runnable não tem
         * dimont para tiparmos. Ele apenas define
         * code block a ser executado em seu run. Creio que poderiamos chamar o método
         * void de uma class Class:method assim esse método
         * seria executado no run. Mas nesse caso vamos referencia uma isntancia de
         * objeto apontando um method de sua class a ser executado
         * e o mesmo sera implementado no run.
         * ClassName::methodVoid não é aceito. O MR entende de outra forma. Realmente
         * precisamos de uma instancia para executar
         * object::method   <=> () -> object.method();
         */

         Usuario user1 = new Usuario("Levi");
        Runnable runnable = user1::printTeste;
        runnable.run();

        /**Referenciando construtores 
         * com referencia de construtores podemos criar nossos proprios factories de Objetos
         * Usaremos diferentes interface funcionais para casos de args
         * Um construtor sem args pode ser expresso a uma IF  Suplier
         * com um arg podemos usar uma function, 2 args biFunction e mais args podemos implementar novas functions
        */

        Supplier<Usuario> fac = Usuario::new;
        Function<String, Usuario> fac2 = Usuario::new;
        BiFunction<String, Integer, Usuario> fac3 = Usuario::new;
        TriFunction<String, Integer, Boolean, Usuario> fac4 = Usuario::new; //interface criada para suportar 3 args

        Usuario u = fac.get();
        Usuario u2 = fac2.apply("José");
        Usuario u3 = fac3.apply("Maria",391);
        Usuario u4 = fac4.apply("Sidney",441, true);
        System.out.println(u);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println(u4);
        separator(4);
        

    }
}
