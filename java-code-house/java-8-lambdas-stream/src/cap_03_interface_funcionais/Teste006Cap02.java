package cap_03_interface_funcionais;

import static util.UtilString.separator;

public class Teste006Cap02 {

    
   public static void main(String[] args) {
	   Runnable r = () -> System.out.println("Runing...");
	   new Thread(r).start();
	   separator(1);
   }

}
