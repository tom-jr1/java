package cap_03_interface_funcionais;

import static util.UtilString.separator;

public class Teste005Cap02 {

    public static void main(String[] args) {
        
        String cpf = "04101-300";
        Validador<String> validadorCpf = (s) -> s.matches("[0-9]{5}-[0-9]{3}");

        System.out.println(validadorCpf.validar(cpf));
        separator(1);

        // não esquecer dos meios que é possivel instanciar uma interface funcional
        /*
         * InterfaceFuncional<T> reference = new Reference() {
         *      @Override
         *      method () {
         *      //implementation of abstract method
         *  }
         * }
         * 
         * E isso pode com lambda ser expresso dessas formas
         * 
         * InterfaceFunctional<T> reference = (T t) -> {codeblock}
         * InterfaceFunctional<T> reference = t -> codeblock // caso seja uma linha de comando, podemos
         *despensar as chaves
         * 
         * 
         */

         // Usamos a annotation @ IterfaceFunctional para que fique explicito ao compilador que se trata
        //  de uma IF. Assim ele mantera a IF dentro das regras para ser uma. Caso quisermos add outro método
        //  abstract o compilador acusar erro, obrigando-nos a manter a interface dentro do padra de um funcional

        
    }
    @FunctionalInterface
    static interface Validador<T> {
        boolean validar(String s);

    }

}
