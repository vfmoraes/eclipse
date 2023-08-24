package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Jogo;

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
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO jogo (codigo, nome, genero, ano) "
				       + "VALUES ("+jogo.getCodigo()+ ", '" + jogo.getNome() + "', '"  
				       + jogo.getGenero() + "', '" + jogo.getAno() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Jogo get(int codigo) {
		Jogo jogo = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 jogo = new Jogo(rs.getInt("codigo"), rs.getString("nome"), rs.getString("genero"), rs.getInt("ano"));
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

	
	public List<Jogo> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	public List<Jogo> getOrderByLogin() {
		return get("nome");		
	}
	
	
	public List<Jogo> getOrderBySexo() {
		return get("ano");		
	}
	
	
	private List<Jogo> get(String orderBy) {	
	
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Jogo u = new Jogo(rs.getInt("codigo"), rs.getString("nome"), rs.getString("genero"), rs.getInt("ano"));
	            jogos.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogos;
	}


	public List<Jogo> getAnoMasculino() {
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo WHERE jogo.ano LIKE 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Jogo u = new Jogo(rs.getInt("codigo"), rs.getString("nome"), rs.getString("genero"), rs.getInt("ano"));
	            jogos.add(u);
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
			Statement st = conexao.createStatement();
			String sql = "UPDATE jogo SET nome = '" + jogo.getNome() + "', genero = '"  
				       + jogo.getGenero() + "', ano = '" + jogo.getAno() + "'"
					   + " WHERE codigo = " + jogo.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM jogo WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean autenticar(String nome, String genero) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo WHERE nome LIKE '" + nome + "' AND genero LIKE '" + genero  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}	
}