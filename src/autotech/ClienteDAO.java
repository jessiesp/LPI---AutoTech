package autotech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Cliente {
	
	public int id, usuario_id;
	String nome, cpf;
	
	public Cliente(int id, String nome, String cpf, int usuario_id) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.usuario_id = usuario_id;
	}
	
	public Cliente(String nome, String cpf, int usuario_id) {
		this.nome = nome;
		this.cpf = cpf;
		this.usuario_id = usuario_id;
	}
	
	public String getInfo() {
		return String.format(
				"ID: %d\tNome: %s\tCPF: %s\tID Usuário: %d", 
				id, nome, cpf, usuario_id
		);
	}
}

public class ClienteDAO {
	
	public static ArrayList<Cliente> getClientes(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.cliente";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Cliente cliente = new Cliente(
					rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getInt("usuario_id")
				);
				clientes.add(cliente);
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return clientes;
	}
	
	public static boolean inserirCliente(Connection conexao, Cliente cliente) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.cliente (nome, cpf, usuario_id) VALUES (?, ?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, cliente.nome);
			st.setString(2, cliente.cpf);
			st.setInt(3, cliente.usuario_id);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}

}
