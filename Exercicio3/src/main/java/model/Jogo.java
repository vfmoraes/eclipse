package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Jogo {
	private int id;
	private String nome;
	private float preco;
	private int quantidade;
	private LocalDateTime dataAnuncio;	
	private LocalDate dataLancamento;
	
	public Jogo() {
		id = -1;
		nome = "";
		preco = 0.00F;
		quantidade = 0;
		dataAnuncio = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		dataLancamento = LocalDate.now().plusMonths(12);
	}

	public Jogo(int id, String nome, float preco, int quantidade, LocalDateTime anuncio, LocalDate v) {
		setId(id);
		setNome(nome);
		setPreco(preco);
		setQuantidade(quantidade);
		setDataAnuncio(anuncio);
		setDataLancamento(v);
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public LocalDateTime getDataAnuncio() {
		return dataAnuncio;
	}

	public void setDataAnuncio(LocalDateTime dataAnuncio) {
		// Pega a Data Atual
		LocalDateTime agora = LocalDateTime.now();
		// Garante que a data de anúncio não pode ser futura
		if (agora.compareTo(dataAnuncio) >= 0)
			this.dataAnuncio = dataAnuncio;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		// a data de anúncio deve ser anterior é data de lancamento.
		if (getDataAnuncio().isBefore(dataLancamento.atStartOfDay()))
			this.dataLancamento = dataLancamento;
	}

	public boolean emLancamento() {
		return LocalDateTime.now().isBefore(this.getDataLancamento().atTime(23, 59));
	}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Jogo: " + nome + "   Preço: R$" + preco + "   Quantidade.: " + quantidade + "   Anúncio: "
				+ dataAnuncio  + "   Data de Lancamento: " + dataLancamento;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Jogo) obj).getID());
	}	
}