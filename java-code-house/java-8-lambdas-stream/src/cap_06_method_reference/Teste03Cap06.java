package cap_06_method_reference;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;
import static util.UtilString.separator;

public class Teste03Cap06 {
    public static void main(String[] args) {
        /*
         * Methodo Reference é uma expressão que podemos usar para simplificar uma
         * chamada de method de uma interface funcional
         * Assim como o lambda, ela tem o poder de simplificar uma expressão evitando
         * instanciações anonimas que ocupariam varias
         * linhas. Até o presente momento não foi possivel usar RM para refenciar
         * métodos com paramentro sem que o mesmo antes seja empaco-
         * tado antes por uma interface function. Vamos iniciar como o uso do reference
         * method para referenciar um método da class usuario
         * para um Consumer<T>
         */

        List<Usuario> list = UtilString.getUsers();
        Consumer<Usuario> consumer1 = new Consumer<Usuario>() {

            @Override
            public void accept(Usuario t) {
                // TODO Auto-generated method stub
                t.tornarModerador();
            }

        };
        // o lambda esta inferindo para o accept do Consumer a execução do tornar
        // moderador para cada chamada do accept. Assim tbm o Method
        // Reference esta o fazendo. Sem a necessidade de apontar o type e usar a
        // referencia do type para chamar o method. Tudo fica de forma
        // inferida
        consumer1 = (Usuario u) -> {
            u.tornarModerador();
        };
        consumer1 = Usuario::tornarModerador;

        /*
         * Esse treixo deixou mais claro a função do RM. Aqui ele Informou o tipo <User>
         * para o consumer e com o reference
         * método apontou qual método é para executar no accept do Consumer refenciando
         * o método pela class 'System.out'. out é uma class
         * que é um type em System. E seu método println
         */
        Consumer<Usuario> print = System.out::println;

        list.forEach(print);
        separator(1);
        list.forEach(consumer1);
        list.forEach(print);
        separator(2);

        /* Vamos fazer o sort com o uso de RM */

        // Na function o ultimo type do daimon vai se tornar o retorno do seu método
        // apply e do primeiro ao penultimo seram args em ordem
        // respectivas
        Function<Usuario, String> keyExtractor = new Function<Usuario, String>() {
            @Override
            public String apply(Usuario t) {
                // TODO Auto-generated method stub
                return t.getNome();
            }
        };

        keyExtractor = Usuario::getNome; // u -> u.getNome()
        list.sort(Comparator.comparing(keyExtractor));
        list.forEach(print);
        separator(3);

        /*
         * Listar pelos pontos. O comparingInt recebe em seu arg uma ToIntFunc<T> o T já
         * inferido pelo type da list
         * ele o seu método retorna um keyExtractor que é do tipo Int que não é inferido
         * pelo retorno do método getPontos e sim pela
         * bifunction que ja retorna do seu apply de forma default um type int caso
         * fosse function, inferiria o type de acordo com o retorno
         * do metodo apos os '::' ele tentaria faz boxing se fosse primitivo e caso não
         * pudesse suportar o type lançaria exception de missMatch
         */
        list.sort(Comparator.comparingInt(Usuario::getPontos));
        list.forEach(print);
        separator(4);

        /*
         * Podemos usar no método compare o thenComparing, que seria um novo sort abaixo
         * do primeiro e poderia seguir adicionando
         * para resolver casos de impates no sort.
         * Ha tambem o Comparator.nullFirst(Comparator c), que organiza os dados nulls
         * para o inicio da fila ou fim se utilizarmos o nullLast
         * Outro é o reversed. que pode ser chamado como o themComparing
         * comparing(f).reversed() para reverter
         * todo o sort que configuramos {[1,2,3] = [3,2,1]}
         */

        list.sort(Comparator
                .nullsLast(Comparator.comparing(Usuario::getNome)
                        .thenComparing(Usuario::getPontos).reversed()));

        /*
         * Dada uma instancia de obejeto podemos usa o MR para referenciar um método seu
         * a ser executa com a interface Funcitonal Runnable
         * neste caso o u1 não esta sendo usado para identificar onde esta a o método a
         * ser executado no run da IF Runnable
         * e sim como o objeto que tera seu método executado. Como se o RM enviasse o
         * objeto com a sua execução para dentro do run
         * Bem diferente das versões anteriores
         */

        Usuario u1 = new Usuario("Jamily");
        Runnable runnable = u1::tornarModerador; // () -> u1.tornaModerador()
        runnable.run();
        System.out.println(u1);
        separator(5);

        /*
         * Dar-se a entender perfeitamente que o MR se adequa a cada interface funcional
         * que ele ira trabalhar
         * para uma function,consumer eu posso informar o método e onde esta
         * OndeEsta::metodo inferindo todos os outros dados necessarios
         * e para o Runnable vc pode chamar uma referencia de objeto e executar seu
         * método objeto::executaEsseMethod
         */

        /*
         * Referenciando construtores para criar factory de objetos. Utilizamos as
         * Interfaces Suplly, Funcition BiFunction e podemos
         * implementar mais IF para criarmos factory que suportem construtores com n
         * args
         * Supply 0 args
         * Function 1 args
         * BiFuction 2 args
         */

        // supplier call o get para executar o reference method
        Supplier<Usuario> factory1 = Usuario::new;
        Function<String, Usuario> factory2 = Usuario::new; // apply(String s) return new Usuario(s).
        BiFunction<String, Integer, Usuario> factory3 = Usuario::new;
        TriFunction<String, Integer, Boolean, Usuario> factory4 = Usuario::new;

        Usuario u2 = factory1.get();
        Usuario u3 = factory2.apply("Johanna");
        Usuario u4 = factory3.apply("Josephine", 344);
        Usuario u5 = factory4.apply("Stephanie", 551, true);

        System.out.println(u2);
        System.out.println(u3);
        System.out.println(u4);
        System.out.println(u5);

        separator(6);

        /* por questões de boxing eu poderia usar o IntBiFunction ou o IntBinaryOperator */
        BiFunction<Integer, Integer, Integer> max = Math::max;
        System.out.println(max.apply(32,5));
    }
}
