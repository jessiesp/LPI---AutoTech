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
		return String.format(
				"ID: %d\tStatus: %s\tData: %s\tID Funcionário: %d\tID Carro: %d",
				id, status, data, funcionarioId, carroId
		);
	}
}

public class OrdemServicoDAO {
	
	public static ArrayList<OrdemServico> getOrdensServico(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.ordemservico";
		ArrayList<OrdemServico> ordensServico = new ArrayList<OrdemServico>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				OrdemServico ordemServico = new OrdemServico(
					rs.getInt("id"), rs.getString("status"), rs.getString("data"), rs.getInt("funcionarioId"), rs.getInt("carroId")
				);
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
		String query = "INSERT INTO autotech.ordemservico (status, data, funcionarioId, carroId) VALUES (?, ?, ?, ?)";
		
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
}
