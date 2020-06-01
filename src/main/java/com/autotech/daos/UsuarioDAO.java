package com.autotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.autotech.models.Usuario;

public class UsuarioDAO {

	public static ArrayList<Usuario> getUsuarios(Connection conexao) throws SQLException {
		Statement st_usuario = null, st_telefone = null;
		String query_usuario = "SELECT * FROM autotech.usuario";
		String query_telefone = "SELECT telefone FROM autotech.telefone WHERE usuario_id = ";
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			st_usuario = conexao.createStatement();
			ResultSet rs = st_usuario.executeQuery(query_usuario);
			while (rs.next()) {
				Usuario usuario = new Usuario(
					rs.getInt("id"), rs.getString("email"), rs.getString("tipo")
				);
				st_telefone = conexao.createStatement();
				ResultSet rs_tels = st_telefone.executeQuery(query_telefone + usuario.id);
				while (rs_tels.next()) {
					usuario.adicionarTelefone(rs_tels.getString("telefone"));
				}
				usuarios.add(usuario);
			}
		} 
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st_usuario != null) st_usuario.close(); 
	        if (st_telefone != null) st_telefone.close(); 
	    }
		
		return usuarios;
	}

	public static Usuario getUsuario(Connection conexao, int idUsuario) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.usuario WHERE id = ";
		Usuario usuario = null;
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query + idUsuario);
			if (rs.next()) {
				usuario = new Usuario(
					idUsuario, rs.getString("email"), rs.getString("tipo")
				);
			}
		} catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } finally {
	        if (st != null) st.close(); 
	    }
		return usuario;
	}
		
	public static int inserirUsuario(Connection conexao, String email, String tipo, String password) throws SQLException {
		PreparedStatement st = null;
		Statement stId = null;
		String query = "INSERT INTO autotech.usuario (email, tipo, password) VALUES (?, ?, ?)";
		String queryId = "SELECT LAST_INSERT_ID() as id";
  
		st = conexao.prepareStatement(query);
		st.setString(1, email);
		st.setString(2, tipo);
		st.setString(3, password);
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
	
	public static boolean inserirTelefone(Connection conexao, int usuario_id, String telefone) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.telefone (telefone, usuario_id) VALUES (?, ?)";

		try {
			st = conexao.prepareStatement(query);
			st.setString(1, telefone);
			st.setInt(2, usuario_id);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
	
	public static boolean deletarUsuario(Connection conexao, int usuarioId) throws SQLException {
		Statement st = null;
		String query = "DELETE FROM autotech.usuario WHERE id = ";
		
		try {
			st = conexao.createStatement();
			st.executeUpdate(query + usuarioId);
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
	
	public static boolean alterarUsuario(Connection conexao, String email, String tipo, String password, String telefone, int usuarioId) throws SQLException {
		PreparedStatement stUsuario = null, stTelefone = null;
		String queryUsuario = "UPDATE autotech.usuario SET email = ?, tipo = ?, password = ? WHERE id = ?";
		String queryTelefone = "UPDATE autotech.telefone SET telefone = ? WHERE usuario_id = ?";
		
		try {
			stUsuario = conexao.prepareStatement(queryUsuario);
			stUsuario.setString(1, email);
			stUsuario.setString(2, tipo);
			stUsuario.setString(3, password);
			stUsuario.setInt(4, usuarioId);
			stTelefone = conexao.prepareStatement(queryTelefone);
			stTelefone.setString(1, telefone);
			stTelefone.setInt(2, usuarioId);
			stUsuario.execute();
			stTelefone.execute();
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
			return false;
	    } 
		finally {
	        if (stUsuario != null) stUsuario.close(); 
	        if (stTelefone != null) stTelefone.close(); 
	    }
		return true;
	}	
}
