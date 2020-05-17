package autotech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Funcionario {
	
	public int id, usuario_id;
	public String nome;
	
	public Funcionario(int id, String nome, int usuario_id) {
		this.id = id;
		this.nome = nome;
		this.usuario_id = usuario_id;
	}
	
	public Funcionario(String nome, int usuario_id) {
		this.nome = nome;
		this.usuario_id = usuario_id;
	}
	
	public String getInfo() {
		return String.format(
			"ID: %d\tNome: %s\tID Usuário: %d", 
			id, nome, usuario_id
		);
	}
}

public class FuncionarioDAO {

	public static ArrayList<Funcionario> getFuncionarios(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.funcionario";
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Funcionario funcionario = new Funcionario(
					rs.getInt("id"), rs.getString("nome"), rs.getInt("usuario_id")
				);
				funcionarios.add(funcionario);
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return funcionarios;
	}
	
	public static boolean inserirFuncionario(Connection conexao, Funcionario funcionario) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.funcionario (nome, usuario_id) VALUES (?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, funcionario.nome);
			st.setInt(2, funcionario.usuario_id);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
}

	 
