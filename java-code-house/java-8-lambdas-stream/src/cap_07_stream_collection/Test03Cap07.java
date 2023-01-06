package cap_07_stream_collection;

import static util.UtilString.separator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;
import util.UtilString;

public class Test03Cap07 {

    public static void main(String[] args) {
        List<Usuario> l1 = UtilString.getUsers();
        /*
         * Quando chamamos o stream podemos encadear operações em pipeline como se cada
         * resultado de cada
         * operação tivesse um retorno e nesse retorno eu posso adicionar mais uma
         * operação. Enquanto houver
         * retorno de cada transação eu posso continuar processando. Lembrando que todo
         * o pipeline não se aplica
         * na lista que se foi chamada a stream. Ela retorna uma nova stream que tenha
         * essas alterações, caso não
         * finalizar como um method stream que não tenha any retorno. E caso usemos o
         * forEach para alterar cada
         * item de uma lista atraves de uma pipe do stream o for each aplicara na list
         * de origem.
         */

        l1.stream()
                .filter((Usuario u) -> u.getPontos() > 130)
                .forEach(System.out::println);// filter->IFPredicate(test)
        separator(1);
        /*
         * Para conseguimos coletar o resultado de uma operacao stream nos podemos usar
         * o método
         * collect que recebe tres argumentos que são tres IF supplier, biConsumer,
         * BiConsumer
         * suplier guarda um factory como o construtor do type de list que desejamos
         * retornar
         * os biConsumer guardam em seus accepts as operações de add e o outro addAll
         * para auxiliar
         * na inserção dos elementos na list resultante
         */

        List<Usuario> listFromCollect = l1.stream().filter(u -> u.getPontos() > 130)
                .collect(new Supplier<ArrayList<Usuario>>() {

                    @Override
                    public ArrayList<Usuario> get() {
                        // TODO Auto-generated method stub
                        return new ArrayList<>();
                    }

                }, new BiConsumer<ArrayList<Usuario>, Usuario>() {

                    @Override
                    public void accept(ArrayList<Usuario> l, Usuario u) {
                        // TODO Auto-generated method stub
                        l.add(u);
                    }

                },
                        new BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>>() {

                            @Override
                            public void accept(ArrayList<Usuario> l, ArrayList<Usuario> m) {
                                // TODO Auto-generated method stub
                                l.addAll(m);
                            }

                        });

        listFromCollect.forEach(System.out::println);
        separator(2);

        /*
         * Mas em vez de fazer todo esse codigo podemos usar o metodo static de
         * Collectors.toList
         * que tem em sua estrutura a operação completa para converter e adicionar os
         * dados
         */

        List<Usuario> l2 = l1.stream().collect(Collectors.toList());
        l2.forEach(System.out::println);
        separator(3);
        System.out.println("l2 is an ArrayList? " + (l2 instanceof ArrayList));

        /*
         * Podemos utilizar o method Collectors.Toset para coletarmos como um set e tbm
         * ha o
         * toCollection(new TypeCollectios) onde passamos uma iniciação de contrutor por
         * um Suplier
         * que sera o type collection que retornará
         */

        double[] i = l1.stream().mapToDouble(Usuario::getPontos).toArray();
        System.out.println(i[0]);
        separator(4);
        /*
         * para trabalharmos com array utilizamos o toArray sem necessidade do collect.
         * Caso for primitivo
         * não precisamos informar paramentros, mas caso for object informamos o
         * construtor do objeto
         * como um splier toArray(Object[]::new)
         */

        double media = l1.stream().mapToInt(Usuario::getPontos).average().orElse(0.0);
        System.out.println(media);

        Integer maiorP = l1.stream()
        .max(Comparator.comparing(Usuario::getPontos)).map(Usuario::getPontos).orElse(0);
        System.out.println(maiorP);
    }
}
