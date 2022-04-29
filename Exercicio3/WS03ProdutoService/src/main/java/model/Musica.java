package model;


public class Musica {
	private int id;
	private String nome;
	private String interpretes;
	private String genero;
	private int lancamento;
	
	public Musica() {
		id = -1;
		nome = "";
		interpretes = "";
		genero = "";
		lancamento = -1;
	}

	public Musica(int id, String nome, String interpretes, String genero, int lancamento) {
		setId(id);
		setNome(nome);
		setInterpretes(interpretes);
		setGenero(genero);
		setLancamento(lancamento);
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
	
	public String getInterpretes() {
		return interpretes;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setInterpretes(String interpretes) {
		this.interpretes = interpretes;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(int lancamento) {
		this.lancamento = lancamento;
	}
	

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Faixa: " + nome + "   Interprete(s):" + interpretes + "   Genêro: " + genero + "   Ano de Lançamento: "
				+ lancamento;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Musica) obj).getID());
	}	
}