# Interface functional

Toda e qualquer interface que tenha apenas um método a ser implementado será candidata a ser expressada
como lambda no momento de uma implementação anonima.
Podemos Criar nossa própria interface funcional e expressa-la por lambda
Temos a annotation opcional para que possamos padronizar a interface como funcional.
@InterfaceFunctional, com ela o compilador passa a mapear a interface como funcional e caso
ser realizada alguma alteração ná interface que há remova do padrão de interfaceFuncional o compilador
não rodará.

Criando interface funcional
~~~ java
@InterfaceFunctional
InterfacePersonalizada<T> {
    void singleMethods(params);
}
~~~

Expressando interfaces:
Vamos usar a class Runnable  como exemplo.

~~~ java
public static void main(String[] args) {
   Runnable r = new Runnable () {
    @Override
    run() {
        System.out.println("Running");
    }
   }
   
   new Thread(r).start();


}
~~~

Usados as possibilidades de simplificação:

~~~ java
public static void main(String[] args) {
    new Thread(() -> System.out.println("Running")).start();
}
~~~

Caso o method da IF ter parâmetros O lambda pode passar 
(Type param) -> {}. 
Podendo reduzir para 
(params) -> {}
ou
params -> {}
e caso for apenas uma linha de instrução na implementação podemos:
params -> codeblock

