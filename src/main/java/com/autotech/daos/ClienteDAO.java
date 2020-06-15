package com.autotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.autotech.models.Cliente;

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

	public static Cliente getClienteByUsuario(Connection conexao, int idUsuario) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.cliente WHERE usuario_id = ";
		Cliente cliente = null;
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query + idUsuario);
			if (rs.next()) {
				cliente = new Cliente(
					rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), idUsuario
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
	
	public static void deletarCliente(Connection conexao, int clienteId) throws SQLException {
		PreparedStatement stServicoHasOrdemServico = null, stOrdemServico = null, stCarro = null, stCliente = null;
		String queryCliente = "DELETE FROM autotech.cliente WHERE id = ?";
		String queryCarro = "DELETE FROM autotech.carro WHERE cliente_id = ?";
		String queryOrdemServico = "DELETE FROM autotech.ordemservico WHERE carro_id IN (SELECT id FROM autotech.carro WHERE cliente_id = ?)";
		String queryServicoHasOrdemServico = "DELETE FROM autotech.servico_has_ordemservico WHERE ordemServico_id IN "
			+ "(SELECT id FROM autotech.ordemservico WHERE carro_id IN "
			+ "(SELECT id FROM autotech.carro WHERE cliente_id = ?))";
		
		try {
			stServicoHasOrdemServico = conexao.prepareStatement(queryServicoHasOrdemServico);
			stServicoHasOrdemServico.setInt(1, clienteId);
			stOrdemServico = conexao.prepareStatement(queryOrdemServico);
			stOrdemServico.setInt(1, clienteId);
			stCarro = conexao.prepareStatement(queryCarro);
			stCarro.setInt(1, clienteId);
			stCliente = conexao.prepareStatement(queryCliente);
			stCliente.setInt(1, clienteId);
			stServicoHasOrdemServico.execute();
			stOrdemServico.execute();
			stCarro.execute();
			stCliente.execute();
		}
		catch (SQLException e) {
			System.out.println(e);
			throw e;
	    } 
		finally {
	        if (stCliente != null) stCliente.close(); 
	        if (stCarro != null) stCarro.close();
	        if (stOrdemServico != null) stOrdemServico.close(); 
	        if (stServicoHasOrdemServico != null) stServicoHasOrdemServico.close(); 

	    }
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
