package com.autotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.autotech.models.OrdemServico;
import com.autotech.models.Servico;

public class OrdemServicoDAO {
	
	public static ArrayList<OrdemServico> getOrdensServico(Connection conexao) throws SQLException {
		Statement st = null, stServico = null;
		String query = "SELECT * FROM autotech.ordemservico";
		String queryServico = "SELECT s.id, s.nome, s.preco FROM autotech.servico_has_ordemservico as o, autotech.servico as s " + 
							  "WHERE s.id = o.servico_id AND o.ordemservico_id = ";
		ArrayList<OrdemServico> ordensServico = new ArrayList<OrdemServico>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				OrdemServico ordemServico = new OrdemServico(
					rs.getInt("id"), rs.getString("status"), rs.getString("data"), rs.getInt("funcionario_id"), rs.getInt("carro_id")
				);
				stServico = conexao.createStatement();
				ResultSet rsServico = stServico.executeQuery(queryServico + ordemServico.id);
				while (rsServico.next()) {
					Servico servico = new Servico(rsServico.getInt("id"), rsServico.getString("nome"), rsServico.getFloat("preco"));
					ordemServico.adicionarServico(servico);
				}
				ordensServico.add(ordemServico);
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return ordensServico;
	}
	
	public static boolean inserirOrdemServico(Connection conexao, OrdemServico ordemServico) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.ordemservico (status, data, funcionario_id, carro_id) VALUES (?, ?, ?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, ordemServico.status);
			st.setString(2, ordemServico.data);
			st.setInt(3, ordemServico.funcionarioId);
			st.setInt(4, ordemServico.carroId);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
	
	public static boolean associarServico(Connection conexao, int servico_id, int ordemServico_id) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.servico_has_ordemservico (servico_id, ordemservico_id) VALUES (?, ?)";

		try {
			st = conexao.prepareStatement(query);
			st.setInt(1, servico_id);
			st.setInt(2, ordemServico_id);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
	
	public static boolean deletarOrdemServico(Connection conexao, int ordemServicoId) throws SQLException {
		Statement stServicoHas = null, statementOrdemServico = null;
		String queryServicoHas = "DELETE FROM autotech.servico_has_ordemServico WHERE ordemServico_id = ";
		String queryOrdemServico = "DELETE FROM autotech.ordemServico WHERE id = ";
		
		try {
			stServicoHas = conexao.createStatement();
			stServicoHas.executeUpdate(queryServicoHas + ordemServicoId);
			statementOrdemServico = conexao.createStatement();
			statementOrdemServico.executeUpdate(queryOrdemServico + ordemServicoId);
			return true;
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (stServicoHas != null) stServicoHas.close(); 
	        if (statementOrdemServico != null) statementOrdemServico.close();
	    }	
		return false;
	}
	
	public static boolean alterarOrdemServico(Connection conexao, String status, String data, int carro_id, int usuarioId) throws SQLException {
		PreparedStatement st = null;
		String query = "UPDATE autotech.ordemServico SET status = ?, data = ?, carro_id = ? WHERE id = ?";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, status);
			st.setString(2, data);
			st.setInt(3, carro_id);
			st.setInt(4, usuarioId);
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
