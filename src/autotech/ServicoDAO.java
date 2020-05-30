package autotech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Servico {
	
	public int id;
	public String nome;
	public float preco;
	
	public Servico(int id, String nome, float preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Servico(String nome, float preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public String getInfo() {
		return String.format(
				"ID: %d\tPlaca: %s\tPreço: %f",
				id, nome, preco
		);
	}
}

public class ServicoDAO {
	
	public static ArrayList<Servico> getServicos(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.servico";
		ArrayList<Servico> servicos = new ArrayList<Servico>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Servico servico = new Servico(
					rs.getInt("id"), rs.getString("nome"), rs.getFloat("preco")
				);
				servicos.add(servico);
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return servicos;
	}
	
	public static boolean inserirServico(Connection conexao, Servico servico) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.servico (nome, preco) VALUES (?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, servico.nome);
			st.setFloat(2, servico.preco);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
	
	public static boolean deletarServico(Connection conexao, int servicoId) throws SQLException {
		Statement st = null;
		String query = "DELETE FROM autotech.servico WHERE id = ";
		
		try {
			st = conexao.createStatement();
			st.executeUpdate(query + servicoId);
			return true;
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return false;
	}
}
