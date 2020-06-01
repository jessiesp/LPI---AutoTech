package com.autotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.autotech.models.Funcionario;

public class FuncionarioDAO {

	public static ArrayList<Funcionario> getFuncionarios(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.funcionario";
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Funcionario funcionario = new Funcionario(
					rs.getInt("id"), rs.getString("nome"), rs.getInt("usuario_id")
				);
				funcionarios.add(funcionario);
			}
		}
		catch (SQLException e ) {
			System.out.println("Erro! " + e);
	    } 
		finally {
	        if (st != null) st.close(); 
	    }	
		return funcionarios;
	}
	
	public static boolean inserirFuncionario(Connection conexao, Funcionario funcionario) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.funcionario (nome, usuario_id) VALUES (?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, funcionario.nome);
			st.setInt(2, funcionario.usuario_id);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
	
	public static boolean deletarFuncionario(Connection conexao, int funcionarioId) throws SQLException {
		Statement st = null;
		String query = "DELETE FROM autotech.funcionario WHERE id = ";
		
		try {
			st = conexao.createStatement();
			st.executeUpdate(query + funcionarioId);
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
	
	public static boolean alterarFuncionario(Connection conexao, String nome, int funcionarioId) throws SQLException {
		PreparedStatement st = null;
		String query = "UPDATE autotech.funcionario SET nome = ? WHERE id = ?";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, nome);
			st.setInt(2, funcionarioId);
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

	 
