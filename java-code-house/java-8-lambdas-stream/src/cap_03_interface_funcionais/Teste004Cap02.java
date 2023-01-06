package cap_03_interface_funcionais;

import static util.UtilString.separator;

public class Teste004Cap02 {
    public static void main(String[] args) {

        // como vimos iterface funcionais são interfaces que possuem apenas um método a
        // ser implementado
        // O lambda expression pode ser usado em qualquer uma dessas interfaces para
        // serem invocadas de modo
        // anonimo e simplificado. Incluindo tbm as interfaces pre-lambdas do java.
        // Vamos de um exemplo da interface
        // Runnable que tem seu método run. vamos apresentar sem e com expression
        // lambdas

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("Teste Interface Funcinal Runnable");
                separator(1);
            }
        };
        new Thread(runnable).start();
        int num = 3;
        // com expression lambda
        new Thread(() -> {
            System.out.println("Teste Runnable lambda expression");
           
            separator(num);
        }).start();


        // Vamos fazer uso de uma interface funcional criada por nos
        // criaremos uma interface de Validação, onde podemos implementar criamos uma implementação de
        // acordo com o que quisermos

        Validador<String> validarCPF = (s) -> s.matches("[0-9]{5}-[0-9]{3}");
        System.out.println(validarCPF.validar("04101-300"));

        // usamos a annotation @InterfaceFunctional para anotar nossa interface como funcional
        // assim o compilador a reconhecerá de forma que apresentará erros quando ela deixar de atender
        // os requisitos para ser um interface funcional

        // Implementação de IF não conseguem modificar variaveis que não esteja em seu scopo


    }

}
