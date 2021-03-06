package com.autotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.autotech.models.CarroModelo;

public class CarroModeloDAO {

	public static ArrayList<CarroModelo> getModelos(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT autotech.carroModelo.id, autotech.carroModelo.nomeModelo, autotech.fabricante.nome AS nomeFabricante FROM autotech.carroModelo, autotech.fabricante WHERE autotech.carroModelo.fabricante_id = autotech.fabricante.id";
		ArrayList<CarroModelo> modelos = new ArrayList<CarroModelo>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				CarroModelo carroModelo = new CarroModelo(
					rs.getInt("id"), rs.getString("nomeModelo"), rs.getString("nomeFabricante")
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
	
	public static boolean deletarModelo(Connection conexao, int carroModeloId) throws SQLException {
		Statement st = null;
		String query = "DELETE FROM autotech.carromodelo WHERE id = ";
		
		try {
			st = conexao.createStatement();
			st.executeUpdate(query + carroModeloId);
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
