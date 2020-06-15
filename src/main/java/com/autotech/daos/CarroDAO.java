package com.autotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.autotech.models.Carro;

public class CarroDAO {
	
	public static ArrayList<Carro> getCarrosAndServicos(Connection conexao, int usuarioId) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.carro, autotech.cliente WHERE autotech.carro.cliente_id = autotech.cliente.id AND autotech.cliente.usuario_id = " + usuarioId;
		ArrayList<Carro> carros = new ArrayList<Carro>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Carro carro = new Carro(
					rs.getInt("id"), rs.getString("placa"), rs.getString("ano"), 
					rs.getString("cor"), rs.getInt("carroModelo_id"), rs.getInt("cliente_id")
				);
				carro.setOrdensServico(OrdemServicoDAO.getOrdensServico(conexao, carro.id));
				carros.add(carro);
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return carros;
	}
	
	public static ArrayList<Carro> getCarros(Connection conexao, int usuarioId) throws SQLException {
		Statement st = null;
		String query = "" +
			"SELECT autotech.carro.id, autotech.carro.placa, autotech.carro.ano, autotech.carro.cor, autotech.carromodelo.nomeModelo, autotech.fabricante.nome AS nomeFabricante " + 
			"FROM autotech.carro, autotech.cliente, autotech.carromodelo, autotech.fabricante " + 
			"WHERE " + 
			"	autotech.carro.cliente_id = autotech.cliente.id AND " + 
			"	autotech.carro.carroModelo_id = autotech.carromodelo.id AND " + 
			"   autotech.carromodelo.fabricante_id = autotech.fabricante.id AND " + 
			"	autotech.cliente.usuario_id = " + usuarioId;
		ArrayList<Carro> carros = new ArrayList<Carro>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Carro carro = new Carro(
					rs.getInt("id"), rs.getString("placa"), rs.getString("ano"), 
					rs.getString("cor"), rs.getString("nomeModelo"), rs.getString("nomeFabricante")
				);
				carros.add(carro);
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return carros;
	}
	
	public static boolean inserirCarro(Connection conexao, Carro carro) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.carro (placa, ano, cor, carroModelo_id, cliente_id) VALUES (?, ?, ?, ?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, carro.placa);
			st.setString(2, carro.ano);
			st.setString(3, carro.cor);
			st.setInt(4, carro.carroModelo_id);
			st.setInt(5, carro.cliente_id);
			st.execute();
			return true;
		} 
		catch (SQLException e) {
			System.out.println(e);
			return false;
	    }
	}
	
	public static boolean deletarCarro(Connection conexao, int carroId) throws SQLException {
		Statement st = null;
		String query = "DELETE FROM autotech.carro WHERE id = ";
		
		try {
			st = conexao.createStatement();
			st.executeUpdate(query + carroId);
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
