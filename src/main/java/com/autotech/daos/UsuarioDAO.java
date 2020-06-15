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
		Statement st = null, st_telefone = null;
		String query = "SELECT * FROM autotech.usuario WHERE id = ";
		String query_telefone = "SELECT telefone FROM autotech.telefone WHERE usuario_id = ";
		Usuario usuario = null;
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query + idUsuario);
			if (rs.next()) {
				usuario = new Usuario(
					idUsuario, rs.getString("email"), rs.getString("tipo")
				);
				st_telefone = conexao.createStatement();
				ResultSet rs_tels = st_telefone.executeQuery(query_telefone + usuario.id);
				while (rs_tels.next()) {
					usuario.adicionarTelefone(rs_tels.getString("telefone"));
				}
			}
		} catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } finally {
	        if (st != null) st.close(); 
	    }
		return usuario;
	}
	
	public static int getUsuarioId(Connection conexao, String email, String senha) {
		PreparedStatement st = null;
		String query = "SELECT id FROM autotech.usuario WHERE email LIKE ? AND password LIKE ?";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, email);
			st.setString(2, senha);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int usuarioId = rs.getInt("id");
				return usuarioId;
			}
			if (st != null) st.close(); 
		} catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    }
		return 0;
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
	
	public static void inserirTelefone(Connection conexao, int usuario_id, String telefone) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.telefone (telefone, usuario_id) VALUES (?, ?)";

		st = conexao.prepareStatement(query);
		st.setString(1, telefone);
		st.setInt(2, usuario_id);
		st.execute();
	}
	
	public static void deletarUsuario(Connection conexao, int usuarioId) throws SQLException {
		PreparedStatement stEndereco = null, stUsuario = null, stTelefone = null;
		String queryUsuario = "DELETE FROM autotech.usuario WHERE id = ?";
		String queryTelefone = "DELETE FROM autotech.telefone WHERE usuario_id = ?";
		String queryEndereco = "DELETE FROM autotech.endereco WHERE usuario_id = ?";
		
		try {
			stTelefone = conexao.prepareStatement(queryTelefone);
			stTelefone.setInt(1, usuarioId);
			stEndereco = conexao.prepareStatement(queryEndereco);
			stEndereco.setInt(1, usuarioId);
			stUsuario = conexao.prepareStatement(queryUsuario);
			stUsuario.setInt(1, usuarioId);
			stTelefone.execute();
			stEndereco.execute();
			stUsuario.execute();
			
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
			throw e;
	    } 
		finally {
	        if (stTelefone != null) stTelefone.close(); 
	        if (stEndereco != null) stEndereco.close();
	        if (stUsuario != null) stUsuario.close();
	    }
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
	
	public static boolean alterarSenha(Connection conexao, String password, int usuarioId) throws SQLException {
		PreparedStatement stUsuario = null;
		String queryUsuario = "UPDATE autotech.usuario SET password = ? WHERE id = ?";
		
		try {
			stUsuario = conexao.prepareStatement(queryUsuario);
			stUsuario.setString(1, password);
			stUsuario.setInt(2, usuarioId);
			stUsuario.execute();
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
			return false;
	    } 
		finally {
	        if (stUsuario != null) stUsuario.close();  
	    }
		return true;
	}	
	
	public static void deletarTelefone(Connection conexao, int usuarioId, String telefone) throws SQLException {
		PreparedStatement stTelefone = null;
		String queryTelefone = "DELETE FROM autotech.telefone WHERE usuario_id = ? AND telefone LIKE ?";
		
		try {
			stTelefone = conexao.prepareStatement(queryTelefone);
			stTelefone.setInt(1, usuarioId);
			stTelefone.setString(2, telefone);
			stTelefone.execute();
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
			throw e;
	    } 
		finally {
	        if (stTelefone != null) stTelefone.close(); 
	    }
	}
}
