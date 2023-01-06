package cap_02_maneira_antiga_e_nova_de_executar_loops;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static util.UtilString.separator;

public class Teste03Cap2 {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);
        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        for (Usuario u : usuarios) {
            System.out.println(u.getNome());
        }
        separator(1);

        // forEach recebe um argumento java.util.function.Consumer que se
        // trata de uma interface funcional, a qual tem apenas um método
        // exatamente o que o define como uma interface funcional
        // a interface Consumer tem o método accept() que recebe
        // um Objeto e executa a ação do método accept.

        // Primeiramente iremos criar uma class que implementa o Consumer
        // e sobreescreve o seu metodo accept, apos isso adicionaremos
        // uma referencia do mesmo na chamada do forEach.
        PrintTeste03 print1 = new PrintTeste03();

        usuarios.forEach(print1);
        separator(2);

        // podemos abstrair a criacao da class. Como? criando uma
        // class anonima. Nos a tipamos com o Consumer<?> e damos
        // new em uma class que será criada no ato. então será cobrado
        // a implementação do método accept como se fosse em uma criação real

        Consumer<Usuario> print2 = new Consumer<Usuario>() {

            @Override
            public void accept(Usuario t) {
                // TODO Auto-generated method stub
                System.out.println(t.getNome());
            }

        };
        usuarios.forEach(print2);
        separator(3);

        // abstraindo mais ainda. Podemos deixar de criar uma referencia
        // a class anonima e diretamente no args do forEach criar a classa
        // anonima

        usuarios.forEach(new Consumer<Usuario>() {

            @Override
            public void accept(Usuario t) {
                // TODO Auto-generated method stub
                System.out.println(t.getNome());

            }

        });
        separator(4);

        // Lambdas. Lambas é uma forma simples de expressar a
        // construção de uma class anonima de um tipo de Interface
        // funcional.
        // Algo como :

        Consumer<Usuario> print3 = new Consumer<Usuario>() {

            @Override
            public void accept(Usuario t) {
                // TODO Auto-generated method stub
                System.out.println(t.getNome());

            }

        };

        usuarios.forEach(print3);
        separator(5);

        // com o lambda expression fica:

        Consumer<Usuario> print4 = (Usuario u) -> {
            System.out.println(u.getNome());
        };
        usuarios.forEach(print4);
        separator(6);

        // bem mais simples essa criacao de uma class anonima
        // de uma interface funcional.
        // O trecho (Usuario u) é automaticamente ligado ao argumento
        // do unico método do método da interface, caso fosse mais de
        // um arg, poderiamos adicionar no lambda
        // Interface<Object> name = (T1 t1, T2 t2).
        // Como na criacao da class o tipo é informado em
        // Consumer<T>, podemos abstrair o tipo entre os parenteses
        // e tbm podemos remover as chaves, quando se trata de uma linha
        // de comando apenas. E por ultimo podemos chamar a expressão
        // lambda sem criar uma referencia. E podemos manter a abstracao
        // da tipagem pois o compilador pegara inferencia da tipagem
        // da list

        usuarios.forEach(u -> System.out.println(u.getNome()));
        separator(7);

        PrintInterface<Usuario> printInterface = (u, s) -> System.out.println(u.getNome()+ s);
        printInterface.accept(user3, "  teste");
        separator(8);
    }

    // -
    // -
    // -
    // -
    // -
    // -
    // -
    // -
    // -
    // -
    // -
    // -
    // -
    // -
    public static class PrintTeste03 implements Consumer<Usuario> {

        @Override
        public void accept(Usuario u) {
            // TODO Auto-generated method stub
            System.out.println(u.getNome());
        }

    }
}
