package cap_06_method_reference;

import static util.UtilString.separator;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;

public class Teste02Cap06 {
    public static <T> void main(String[] args) {
        List<Usuario> list = UtilString.getUsers();

        /*
         * Vamos tornar todos os users moderadores. temos um método na class user que
         * faz isso. Então a passamos pela iteração de todos
         * os meus usuarios da list com um forEach e tudo estará pronto. Lembrando que
         * isso pode ser realizado de forma mais concisa usando
         * o lambda expression para declarar a class anonima Consumer<T> que o forEach
         * aguarda.
         */
        Consumer<Usuario> tornarModeradores = new Consumer<Usuario>() {

            @Override
            public void accept(Usuario t) {
                // TODO Auto-generated method stub
                t.tornarModerador();
            }

        };

        // expresso em lambda
        tornarModeradores = (u) -> u.tornarModerador();

        // expresso com a funcionalidade Method Reference
        tornarModeradores = Usuario::tornarModerador;

        list.forEach(tornarModeradores);
        Consumer<Usuario> print = System.out::println;

        list.forEach(print);
        separator(1);

        /* Vamos usar o RM no comparing do Comparator para ordenar a list de usuario */
        Comparator<Usuario> comparator1 = new Comparator<Usuario>() {

            @Override
            public int compare(Usuario o1, Usuario o2) {
                // TODO Auto-generated method stub
                return o1.getNome().compareTo(o2.getNome());
            }

        };

        // ate o momento não vejo como expressar esse lambda como RM
        comparator1 = (u1, u2) -> u1.getNome().compareTo(u2.getNome());

        comparator1 = Comparator.comparing(new Function<Usuario, String>() {

            @Override
            public String apply(Usuario t) {
                // TODO Auto-generated method stub
                return t.getNome();
            }

        });

        comparator1 = Comparator.comparing((u) -> u.getNome());
        comparator1 = Comparator.comparing(Usuario::getNome);

        list.sort(comparator1);
        list.forEach(print);
        separator(2);

        /*
         * Apresentar o método default do Comparator thenComparing. Com eles podemos
         * configurar um comparing secundario, caso o principal
         * der empate ele seguirar o fluxo do proximo comparing informado. Segue a
         * sintaxe
         */

        list.sort(Comparator.comparingInt(Usuario::getPontos)
                .thenComparing(Usuario::getNome));

        list.forEach(print);
        separator(3);

        /*
         * Tudo isso ainda pode ser empacotado pelo método static nullLast ou nullFirst
         * que configura os valores nulls da lista
         * para o começo ou fim da list no momento do sort. Segue a sintaxe:
         */

        list.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getNome).thenComparing(Usuario::getPontos)));

        /*
         * Um método default de muito interesse é o reverse que entrega o sort final
         * inverso. Sintaxe
         */

        list.sort(Comparator.comparing(Usuario::getNome).reversed());
        list.forEach(print);
        separator(4);

        /*
         * Referenciando métodos de instancia
         * dado um objeto podemos chamar um método da sua class por meio de RM
         */

        Usuario u1 = new Usuario("Naruto");
        Runnable runnable = u1::tornarModerador; // u1-> u1.tornarModerador(); || new Runnable e implements o run
        runnable.run();
        System.out.println(u1);
        separator(5);

        /* Referenciando métodos que recebe argumento */

        list.forEach((u) -> System.out.println(u));
        list.forEach(System.out::println);
        separator(6);

        /*
         * Podemos usar MR para criar factories de class. Vamos começar criando um
         * factory de Usuario a partir de seu construtor default
         * que não tem args present
         */

        Supplier<Usuario> factory1 = new Supplier<Usuario>() {

            @Override
            public Usuario get() {
                // TODO Auto-generated method stub
                return new Usuario();
            }

        };

        factory1 = () -> new Usuario();
        factory1 = Usuario::new;
        Usuario u2 = factory1.get();
        System.out.println(u2);
        separator(7);

        /*Para uma referencia ao construtor com um args usamos a Function IF que é capaz de suportar */

        Function<String, Usuario> factory2 = new Function<String,Usuario>() {

            @Override
            public Usuario apply(String t) {
                // TODO Auto-generated method stub
                return new Usuario(t);
            }
            
        };

        factory2 = (s) -> new Usuario(s);
        factory2 = Usuario::new;

        Usuario u3 = factory2.apply("Jose");
        System.out.println(u3);
        separator(8);


        /*Dois args no constructor */
        BiFunction<String, Integer, Usuario> factory3 = Usuario::new;
        Usuario u4 = factory3.apply("Joao", 10);
        System.out.println(u4);
        separator(9);

        /*Caso quisessemos com mais args deveriamos implementar nos mesmo uma IF que suportasse tres args */
        TriFunction<String, Integer, Boolean, Usuario> factory4 = new TriFunction<String, Integer,Boolean, Usuario>() {

            @Override
            public Usuario apply(String t, Integer u, Boolean X) {
                // TODO Auto-generated method stub
                return new Usuario(t, u, X);
            }
            
        };

        factory4 = (s, i, b) -> new Usuario(s, i, b);

        Usuario u5 = factory4.apply("Maria", 11, true);
        System.out.println(u5);
        separator(10);

        BiFunction<Integer, Integer, Integer> maxBiFunction = new BiFunction<Integer,Integer,Integer>() {

            @Override
            public Integer apply(Integer t, Integer u) {
                // TODO Auto-generated method stub
                return Math.max(t, u);
            }
            
        };
        maxBiFunction = (i1,i2) -> Math.max(i1, i2);
        maxBiFunction = Math::max;

        IntBinaryOperator maxBinaryOperator = new IntBinaryOperator() {

            @Override
            public int applyAsInt(int left, int right) {
                // TODO Auto-generated method stub
                return Math.max(left, right);
            }
            
        };

        System.out.println(maxBiFunction.apply(6, 5));
        System.out.println(maxBinaryOperator.applyAsInt(6, 125));
        
    }
}
