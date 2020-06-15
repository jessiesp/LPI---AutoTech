package com.autotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.autotech.models.Endereco;

public class EnderecoDAO {
	
	public static ArrayList<Endereco> getEnderecos(Connection conexao, int usuarioId) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.endereco WHERE usuario_id = ";
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query + usuarioId);
			while (rs.next()) {
				Endereco endereco = new Endereco(
					rs.getInt("id"), rs.getString("rua"), rs.getString("numero"), 
					rs.getString("complemento"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("uf"), rs.getInt("usuario_id")
				);
				enderecos.add(endereco);
			}
		}
			catch (SQLException e ) {
				System.out.println("Erro! " + e);
		    } 
			finally {
		        if (st != null) st.close(); 
		    }	
			return enderecos;	
		}
	
	public static void inserirEndereco(Connection conexao, int usuario_id, String rua, String numero, String complemento, String bairro, String cidade, String uf) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.endereco (rua, numero, complemento, bairro, cidade, uf, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		st = conexao.prepareStatement(query);
		st.setString(1, rua);
		st.setString(2, numero);
		st.setString(3, complemento);
		st.setString(4, bairro);
		st.setString(5, cidade);
		st.setString(6, uf);
		st.setInt(7, usuario_id);
		st.execute();
	}
	
	public static boolean deletarEndereco(Connection conexao, int enderecoId, int usuarioId) throws SQLException {
		PreparedStatement st = null;
		String query = "DELETE FROM autotech.endereco WHERE usuario_id = ? AND id = ?";
		
		try {
			st = conexao.prepareStatement(query);
			st.setInt(1, usuarioId);
			st.setInt(2, enderecoId);
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
