package autotech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class CarroModelo {
	
	public int id, fabricante_id;
	public String nomeModelo;
	
	public CarroModelo(int id, String nomeModelo, int fabricante_id) {
		this.id = id;
		this.nomeModelo = nomeModelo;
		this.fabricante_id = fabricante_id;
	}
	
	public CarroModelo(String nomeModelo, int fabricante_id) {
		this.nomeModelo = nomeModelo;
		this.fabricante_id = fabricante_id;
	}
	
	public String getInfo() {
		return String.format("ID: %d\tModelo: %s\tID Usuário: %s",
				id, nomeModelo, fabricante_id);
	}
}

public class CarroModeloDAO {

	public static ArrayList<CarroModelo> getModelos(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.carroModelo";
		ArrayList<CarroModelo> modelos = new ArrayList<CarroModelo>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				CarroModelo carroModelo = new CarroModelo(
					rs.getInt("id"), rs.getString("nomeModelo"), rs.getInt("fabricante_id")
				);
				modelos.add(carroModelo);
			}
		}
			catch (SQLException e ) {
				System.out.println("Erro! " + e);
		    } 
			finally {
		        if (st != null) st.close(); 
		    }	
			return modelos;	
		}
	
	public static boolean inserirModelo(Connection conexao, CarroModelo carroModelo) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.carroModelo (nomeModelo, fabricante_id) VALUES (?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, carroModelo.nomeModelo);
			st.setInt(2, carroModelo.fabricante_id);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}	
}
