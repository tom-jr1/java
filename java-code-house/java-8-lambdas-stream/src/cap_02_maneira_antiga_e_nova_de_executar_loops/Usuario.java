package cap_02_maneira_antiga_e_nova_de_executar_loops;


public class Usuario {

	private String nome;
	private int pontos;
	private boolean moderador;

	public Usuario() {
	}

	public Usuario(String nome) {
		super();
		this.nome = nome;
	}

	public Usuario(String nome, int pontos) {
		super();
		this.nome = nome;
		this.pontos = pontos;
	}
	
	
	public Usuario(String nome, int pontos, boolean moderador) {
		super();
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = moderador;
	}

	public void  printTeste() {System.out.println("Teste call MR."); }
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public boolean isModerador() {
		return moderador;
	}

	public void tornarModerador() {
		this.moderador = true;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", pontos=" + pontos + ", moderador=" + moderador + "]";
	}

	
}
