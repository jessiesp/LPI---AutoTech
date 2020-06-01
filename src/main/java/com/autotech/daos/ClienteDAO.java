package com.autotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.autotech.models.Cliente;
import com.autotech.models.Usuario;

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

	public static Cliente getCliente(Connection conexao, int idCliente) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.cliente WHERE id = ";
		Cliente cliente = null;
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query + idCliente);
			if (rs.next()) {
				cliente = new Cliente(
					idCliente, rs.getString("nome"), rs.getString("cpf"), rs.getInt("usuario_id")
				);
			}
		} catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } finally {
	        if (st != null) st.close(); 
	    }
		return cliente;
	}
	
	public static boolean inserirCliente(Connection conexao, String nome, String cpf, int usuarioId) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.cliente (nome, cpf, usuario_id) VALUES (?, ?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, nome);
			st.setString(2, cpf);
			st.setInt(3, usuarioId);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
	
	public static boolean deletarCliente(Connection conexao, int clienteId) throws SQLException {
		Statement st = null;
		String query = "DELETE FROM autotech.cliente WHERE id = ";
		
		try {
			st = conexao.createStatement();
			st.executeUpdate(query + clienteId);
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
	
	public static boolean alterarCliente(Connection conexao, int clienteId, String nome, String cpf) throws SQLException {
		PreparedStatement st = null;
		String query = "UPDATE autotech.cliente SET nome = ?, cpf = ? WHERE id = ?";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, nome);
			st.setString(2, cpf);
			st.setInt(3, clienteId);
			st.execute();
			return true;
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
			return false;
	    } 
		finally {
	        if (st != null) st.close(); 
	    }		
	}	
}
