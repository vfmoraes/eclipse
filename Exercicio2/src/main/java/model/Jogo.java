package model;

public class Jogo {
	private int codigo;
	private String nome;
	private String genero;
	private int ano;
	
	public Jogo() {
		this.codigo = -1;
		this.nome = "";
		this.genero = "";
		this.ano = -1;
	}
	
	public Jogo(int codigo, String nome, String genero, int ano) {
		this.codigo = codigo;
		this.nome = nome;
		this.genero = genero;
		this.ano = ano;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Jogo [codigo=" + codigo + ", nome=" + nome + ", genero=" + genero + ", ano=" + ano + "]";
	}	
}
