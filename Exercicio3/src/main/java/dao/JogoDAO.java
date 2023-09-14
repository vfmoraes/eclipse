package dao;

import model.Jogo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class JogoDAO extends DAO {	
	public JogoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Jogo jogo) {
		boolean status = false;
		try {
			String sql = "INSERT INTO jogo (nome, preco, quantidade, dataanuncio, datalancamento) "
		               + "VALUES ('" + jogo.getNome() + "', "
		               + jogo.getPreco() + ", " + jogo.getQuantidade() + ", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(jogo.getDataAnuncio()));
			st.setDate(2, Date.valueOf(jogo.getDataLancamento()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Jogo get(int id) {
		Jogo jogo = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 jogo = new Jogo(rs.getInt("id"), rs.getString("nome"), (float)rs.getDouble("preco"), 
	                				   rs.getInt("quantidade"), 
	        			               rs.getTimestamp("dataanuncio").toLocalDateTime(),
	        			               rs.getDate("datalancamento").toLocalDate());
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogo;
	}
	
	
	public List<Jogo> get() {
		return get("");
	}

	
	public List<Jogo> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Jogo> getOrderByNome() {
		return get("nome");		
	}
	
	
	public List<Jogo> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Jogo> get(String orderBy) {
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Jogo p = new Jogo(rs.getInt("id"), rs.getString("nome"), (float)rs.getDouble("preco"), 
	        			                rs.getInt("quantidade"),
	        			                rs.getTimestamp("dataanuncio").toLocalDateTime(),
	        			                rs.getDate("datalancamento").toLocalDate());
	            jogos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogos;
	}
	
	
	public boolean update(Jogo jogo) {
		boolean status = false;
		try {  
			String sql = "UPDATE jogo SET nome = '" + jogo.getNome() + "', "
					   + "preco = " + jogo.getPreco() + ", " 
					   + "quantidade = " + jogo.getQuantidade() + ","
					   + "dataanuncio = ?, " 
					   + "datalancamento = ? WHERE id = " + jogo.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(jogo.getDataAnuncio()));
			st.setDate(2, Date.valueOf(jogo.getDataLancamento()));
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
			st.executeUpdate("DELETE FROM jogo WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}