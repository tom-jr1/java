package util;

import java.util.ArrayList;
import java.util.List;

import cap_02_maneira_antiga_e_nova_de_executar_loops.Usuario;

public class UtilString {

	public static void separator(int i) {
		System.out.println("****" + i + "****");
	}

	public static List<Usuario> getUsers() {
		Usuario user1 = new Usuario("Paulo Silveira", 150, true);
		Usuario user2 = new Usuario("Rodrigo Turini", 120, true);
		Usuario user3 = new Usuario("Guilherme Silveira", 90);
		Usuario user4 = new Usuario("Sergio Lopes", 120);
		Usuario user5 = new Usuario("Adriano Almeida", 100);
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		usuarios.add(user4);
		usuarios.add(user5);
		return usuarios;
	}
}
