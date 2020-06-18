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
	
	public static ArrayList<OrdemServico> getOrdensServico(Connection conexao, int carroId) throws SQLException {
		Statement stServico = null;
		PreparedStatement st = null;
		String query = "SELECT * FROM autotech.ordemservico WHERE carro_id = ? ORDER BY data DESC";
		String queryServico = "SELECT s.id, s.nome, s.preco FROM autotech.servico_has_ordemservico as o, autotech.servico as s " + 
							  "WHERE s.id = o.servico_id AND o.ordemservico_id = ";
		ArrayList<OrdemServico> ordensServico = new ArrayList<OrdemServico>();
		
		try {
			st = conexao.prepareStatement(query);
			st.setInt(1, carroId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				OrdemServico ordemServico = new OrdemServico(
					rs.getInt("id"), rs.getString("status"), rs.getString("data"), rs.getString("comentarios"),rs.getInt("funcionario_id"), rs.getInt("carro_id")
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

	public static int countOrdensServico(Connection conexao, int carroId) throws SQLException {
		PreparedStatement st = null;
		String query = "SELECT COUNT(id) AS count FROM autotech.ordemservico WHERE carro_id = ? ORDER BY data DESC";
		int result = 0;
		
		try {
			st = conexao.prepareStatement(query);
			st.setInt(1, carroId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				result = rs.getInt("count");
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return result;
	}

	public static ArrayList<OrdemServico> getOrdensServicoByStatus(Connection conexao, String status) throws SQLException {
		Statement stServico = null;
		PreparedStatement st = null;
		String query = "SELECT * FROM autotech.ordemservico WHERE status LIKE ? ORDER BY data DESC";
		String queryServico = "SELECT s.id, s.nome, s.preco FROM autotech.servico_has_ordemservico as o, autotech.servico as s " + 
							  "WHERE s.id = o.servico_id AND o.ordemservico_id = ";
		ArrayList<OrdemServico> ordensServico = new ArrayList<OrdemServico>();
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, status);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				OrdemServico ordemServico = new OrdemServico(
					rs.getInt("id"), rs.getString("status"), rs.getString("data"), rs.getString("comentarios"),rs.getInt("funcionario_id"), rs.getInt("carro_id")
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
	
	public static OrdemServico getOrdemServico(Connection conexao, int idOrdemServico) throws SQLException {
		Statement st = null, stServico = null;
		String query = "SELECT * FROM autotech.ordemservico WHERE id = ";
		String queryServico = "SELECT s.id, s.nome, s.preco FROM autotech.servico_has_ordemservico as o, autotech.servico as s " + 
							  "WHERE s.id = o.servico_id AND o.ordemservico_id = ";
		OrdemServico ordemServico = null;
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query + idOrdemServico);
			while (rs.next()) {
				ordemServico = new OrdemServico(
					idOrdemServico, rs.getString("status"), rs.getString("data"), rs.getString("comentarios"),rs.getInt("funcionario_id"), rs.getInt("carro_id")
				);
				stServico = conexao.createStatement();
				ResultSet rsServico = stServico.executeQuery(queryServico + idOrdemServico);
				while (rsServico.next()) {
					Servico servico = new Servico(rsServico.getInt("id"), rsServico.getString("nome"), rsServico.getFloat("preco"));
					ordemServico.adicionarServico(servico);
				}
				
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }
		return ordemServico;
	}
	
	public static int inserirOrdemServico(
	Connection conexao, String status, String data, String comentarios, int carro_id) throws SQLException {
		PreparedStatement st = null;
		Statement stId = null;
		String query = "INSERT INTO autotech.ordemservico (status, data, comentarios, carro_id) VALUES ('emAnalise', ?, ?, ?)";
		String queryId = "SELECT LAST_INSERT_ID() as id";
		
		st = conexao.prepareStatement(query);
		st.setString(1, data);
		st.setString(2, comentarios);
		st.setInt(3, carro_id);
		st.execute();
		stId = conexao.createStatement();
		ResultSet rs = stId.executeQuery(queryId);
		if (rs.next()) {
			return rs.getInt("id");
		}
		else {
			throw new SQLException("No last id fetched");
		}
	}
	
	public static void associarServico(Connection conexao, int servico_id, int ordemServico_id) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.servico_has_ordemservico (servico_id, ordemservico_id) VALUES (?, ?)";

		st = conexao.prepareStatement(query);
		st.setInt(1, servico_id);
		st.setInt(2, ordemServico_id);
		st.execute();
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
	
	public static void desassociarServicos(Connection conexao, int ordemServicoId) throws SQLException {
		Statement stServicoHas = null;
		String queryServicoHas = "DELETE FROM autotech.servico_has_ordemServico WHERE ordemServico_id = ";
		
		try {
			stServicoHas = conexao.createStatement();
			stServicoHas.executeUpdate(queryServicoHas + ordemServicoId);
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (stServicoHas != null) stServicoHas.close(); 
	    }	
	}
	
	public static boolean alterarOrdemServico(Connection conexao, String data, String comentarios, int carro_id, int ordemServicoId) throws SQLException {
		PreparedStatement st = null;
		String query = "UPDATE autotech.ordemServico SET data = ?, comentarios = ?, carro_id = ? WHERE id = ?";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, data);
			st.setString(2, comentarios);
			st.setInt(3, carro_id);
			st.setInt(4, ordemServicoId);
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
	
	public static boolean alterarStatus(Connection conexao, String status, int ordemServicoId) throws SQLException {
		PreparedStatement st = null;
		String query = "UPDATE autotech.ordemServico SET status = ? WHERE id = ?";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, status);
			st.setInt(2, ordemServicoId);
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
