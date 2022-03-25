package maven.demo;

public class Playlist {
	private int id;
	private String nome;
	private String interpretes;
	private String genero;
	
	public Playlist() {
		this.id = -1;
		this.nome = "";
		this.interpretes = "";
		this.genero = "";
	}
	
	public Playlist(int id, String nome, String interpretes, String genero) {
		this.id = id;
		this.nome = nome;
		this.interpretes = interpretes;
		this.genero = genero;
	}
	
	public int getId() {
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
	
	public String getInterpretes() {
		return interpretes;
	}
	
	public void setInterpretes(String interpretes) {
		this.interpretes = interpretes;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", nome=" + nome + ", interpretes=" + interpretes + ", genero=" + genero + "]";
	}	
}
