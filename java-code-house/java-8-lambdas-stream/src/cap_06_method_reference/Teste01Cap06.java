package cap_06_method_reference;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;
import static util.UtilString.separator;

public class Teste01Cap06 {
    public static void main(String[] args) {

        /*
         * Méthod Reference pode ser usado para substitui um lambda. A principio vamos
         * criar um method que chama a exeução de um método
         * da lista de Usuario
         */
        List<Usuario> list = UtilString.getUsers();

        // ao inves de fazer esse codigo
        // list.forEach(u -> u.tornarModerador());

        // podemos substituir por
        list.forEach(Usuario::tornarModerador);

        list.forEach(i -> System.out.println(i));
        separator(1);

        /*
         * No exemplo acima fica claro que estamos fazendo o uso do method reference na
         * instanciação anonima de uma class
         * que implementa Consumer. Que ja sabemos que é o arg de forEach
         */

        Consumer<Usuario> consumer1 = new Consumer<Usuario>() {

            @Override
            public void accept(Usuario u) {
                // TODO Auto-generated method stub
                u.tornarModerador();
            }

        };

        // como sabemos este consumer acima pode ser expresso com lambda
        Consumer<Usuario> consumer1Lambda = (u) -> u.tornarModerador();

        // e o mesmo agora pode ser expresso com o Reference Method
        Consumer<Usuario> consumer1RefMethod = Usuario::tornarModerador;

        list.forEach(consumer1);
        list.forEach(consumer1Lambda);
        list.forEach(consumer1RefMethod);

        /*
         * Com isso podemos chegar em uma conclusão. Method Reference pode ser usa como
         * o lambda para traduzir uma interface
         * funcional.
         */

        /*
         * No Cap anterior fizemos uso do método static Comparator.comparing para
         * ordenar a lista de varias formas
         * o comparing recebia um um lambda que era representação de uma function. No
         * exemplo abaixo iremos demonstrar como usaremos
         * o Reference Method para expressar essa a function
         * Iremos fazer de forma escalar. começando de forma anonima e evoluir ate o
         * Reference
         */

        Function<Usuario, String> function1 = new Function<Usuario, String>() {

            @Override
            public String apply(Usuario t) {
                // TODO Auto-generated method stub
                return t.getNome();
            }

        };

        Function<Usuario, String> function2 = u -> u.getNome();

        Function<Usuario, String> function3 = Usuario::getNome;

        /*
         * Lembrando que todas essas instancias poderiam ser abstrida ao adicionar
         * diretamente elas como arg onde são requeridas
         */

        list.sort(Comparator.comparing(function1));
        list.sort(Comparator.comparing(function2));
        list.sort(Comparator.comparing(function3));
        list.sort(Comparator.comparing(Usuario::getNome));
        list.forEach(i -> System.out.println(i));
        separator(2);

        /*
         * podemos como anteriormente fazer comparação por outras propriedades com o
         * Reference Method. Vamos fazer o sort pelos pontos
         * E usar um static para casos de empara ordenar por outra propriedade
         * Notas que o Comparator.comparing é uma factory de comparator que é o arg do
         * método sort. Tando la List quando do Collections
         */

        Function<Usuario, Integer> function4 = Usuario::getPontos;
        Function<Usuario, String> function5 = Usuario::getNome;

        list.sort(Comparator.comparing(function4).thenComparing(function5));
        list.forEach(i -> System.out.println(i));
        separator(3);

        /*
         * O comparing pode ser empacotado em uma outro método static. Exemplos é o
         * nullLast e nullFirst
         * que organiza o sort para que elementos null fiquem no começo ou fim da list
         * 
         * Mais uma propriedade que podemos adicionar é o reversed que chamamos a partir
         * de um comparator
         * esse é um default métod que revert a ordem a qual configuramos
         */

        Comparator<Usuario> comparator1 = Comparator.comparing(Usuario::getPontos).thenComparing(Usuario::getNome);
        Comparator<Usuario> comparator2 = Comparator.nullsFirst(comparator1);

        list.sort(comparator2.reversed());
        list.forEach(i -> System.out.println(i));
        separator(3);

        /* Podemos criar um reference method que invoca um dos métodos de um objeto */

        Usuario u = new Usuario("Antonio Carlos", 4000);
        System.out.println(u);
        Runnable runnable = u::tornarModerador;
        runnable.run();
        System.out.println(u);
        separator(4);

        /*
         * A pripori IF que não recebem args são facilmente expressas por Reference
         * methdos
         */

        // Essas duas expressões são equivalente em execução
        Consumer<Usuario> print1Consumer = u1 -> System.out.println(u1);
        print1Consumer = System.out::println;
        list.forEach(print1Consumer);
        separator(5);

        /*
         * Vamos aprender a salvar uma instancia de um novo objeto na interface
         * funcional Supllier<T>
         */

        Supplier<Usuario> supplier = new Supplier<Usuario>() {

            @Override
            public Usuario get() {
                // TODO Auto-generated method stub
                return new Usuario();
            }
        };

        // Expresso por Reference method
        supplier = Usuario::new;

        Usuario u2 = supplier.get();
        u2.setNome("Teste");
        System.out.println(u2);
        Usuario u3 = supplier.get();
        u3.setNome("Teste2");
        System.out.println(u3);
        separator(6);

        /*
         * quando quisermos fazer factory de objeto que tenha 1 argumento no construtor.
         * Podemos criar a partir de uma Function
         */

        Function<String, Usuario> functionFactoryUsuario = new Function<String, Usuario>() {

            @Override
            public Usuario apply(String t) {
                // TODO Auto-generated method stub
                return new Usuario(t);
            }

        };

        functionFactoryUsuario = Usuario::new;

        Usuario u4 = functionFactoryUsuario.apply("Naruto");
        System.out.println(u4);
        separator(7);

        /**
         * Exemplo caso queiramos fazer um factory de um objeto com dois args no
         * construtor
         * Para este caso usamos uma BiFunction<T,T,T>
         */

        BiFunction<String, Integer, Usuario> factoryBi = new BiFunction<String, Integer, Usuario>() {

            @Override
            public Usuario apply(String t, Integer u) {
                // TODO Auto-generated method stub
                return new Usuario(t, u);
            }

        };

        factoryBi = Usuario::new;

        Usuario u5 = factoryBi.apply("Vegeta", 1);
        System.out.println(u5);
        separator(8);

        /**
         * Caso quisese fazer factory com capacidade de mais args precisaria Fazer minha
         * propria function
         */

        TriFunction<String, Integer, Boolean, Usuario> factoryTri = new TriFunction<String, Integer, Boolean, Usuario>() {

            @Override
            public Usuario apply(String t, Integer u, Boolean r) {
                // TODO Auto-generated method stub
                return new Usuario(t, u, r);
            }

        };

        Usuario u6 = factoryTri.apply("Sasuke", 22, Boolean.TRUE);
        System.out.println(u6);
        separator(8);


        ToIntBiFunction<Integer, Integer> biFunctionMax = new ToIntBiFunction<Integer,Integer>() {

            @Override
            public int applyAsInt(Integer t, Integer u) {
                // TODO Auto-generated method stub
                return Math.min(t,u);
            }
            
        };
        biFunctionMax = Math::max;

        int i = biFunctionMax.applyAsInt(93, 5);
        System.out.println(i);
    }
}
