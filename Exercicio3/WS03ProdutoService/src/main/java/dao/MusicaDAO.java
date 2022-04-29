package dao;

import model.Musica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MusicaDAO extends DAO {	
	public MusicaDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Musica musica) {
		boolean status = false;
		try {
			String sql = "INSERT INTO musica (id, nome, interpretes, genero, lancamento) "
		               + "VALUES (" + musica.getID() + ", '"
		               + musica.getNome() + "', '"
		               + musica.getInterpretes() + "', '" 
		               + musica.getGenero() + "', " 
		               + musica.getLancamento() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Musica get(int id) {
		Musica musica = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM musica WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 musica = new Musica(rs.getInt("id"), rs.getString("nome"), rs.getString("interpretes"), rs.getString("genero"), rs.getInt("lancamento"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return musica;
	}
	
	
	public List<Musica> get() {
		return get("");
	}

	
	public List<Musica> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Musica> getOrderByNome() {
		return get("nome");		
	}
	
	
	public List<Musica> getOrderByLancamento() {
		return get("lancamento");		
	}
	
	
	private List<Musica> get(String orderBy) {
		List<Musica> musicas = new ArrayList<Musica>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM musica" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Musica p = new Musica(rs.getInt("id"), rs.getString("nome"), rs.getString("interpretes"), rs.getString("genero"), rs.getInt("lancamento") );
	            musicas.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return musicas;
	}
	
	
	public boolean update(Musica musica) {
		boolean status = false;
		try {  
			String sql = "UPDATE musica SET nome = '" + musica.getNome() + "', "
					   + "interpretes = '" + musica.getInterpretes() + "', " 
					   + "genero = '" + musica.getGenero() + "', "
					   + "lancamento = " + musica.getLancamento() 
					   + " WHERE id = " + musica.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM musica WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}