package autotech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class OrdemServico {
	
	public int id, funcionarioId, carroId;
	public String status;
	public String data;
	public ArrayList<Servico> servicos = new ArrayList<Servico>();
	
	public OrdemServico(int id, String status, String data, int funcionarioId, int carroId) {
		this.id = id;
		this.status = status;
		this.data = data;
		this.funcionarioId = funcionarioId;
		this.carroId = carroId;
	}
	
	public OrdemServico(String status, String data, int funcionarioId, int carroId) {
		this.status = status;
		this.data = data;
		this.funcionarioId = funcionarioId;
		this.carroId = carroId;
	}
	
	public String getInfo() {
		String 	servicos = "";
		for (Servico s : this.servicos) {
			servicos += String.format("%s - ", s.nome);
		}
		return String.format(
				"ID: %d\tStatus: %s\tData: %s\tID Funcionário: %d\tID Carro: %d\tServiços: %s",
				id, status, data, funcionarioId, carroId, servicos
		);
	}

	public void adicionarServico(Servico servico) {
		this.servicos.add(servico);
	}
}

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
}
