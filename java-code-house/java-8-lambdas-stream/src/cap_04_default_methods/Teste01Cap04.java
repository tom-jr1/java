package cap_04_default_methods;

import static util.UtilString.separator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

public class Teste01Cap04 {
    public static void main(String[] args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);

        Consumer<Usuario> consumer = new Consumer<Usuario>() {
            @Override
            public void accept(Usuario t) {
                // TODO Auto-generated method stub
                System.out.println("before execute...");
            }
        };

        usuarios.forEach(consumer.andThen(u -> System.out.println(u.getNome())));
        separator(1);
        /**
         * Outro default método que iremos ver é o removeIf. Um default método da
         * interface Collection a
         * qual List herda.
         * O predicate se trata de um interface funcional, sendo assim, podemos
         * expressa-lo como lambda.
         */

        usuarios.removeIf(u -> u.getPontos() > 160);
        usuarios.forEach(u -> System.out.println(u.getNome()));
        separator(2);

    }

}
