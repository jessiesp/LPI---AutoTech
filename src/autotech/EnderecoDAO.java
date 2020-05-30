package autotech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Endereco {
	
	public int id, usuario_id;
	public String rua, numero, complemento, bairro;
	
	public Endereco(int id, String rua, String numero, String complemento, String bairro, int usuario_id) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.usuario_id = usuario_id;
	}
	
	public Endereco(String rua, String numero, String complemento, String bairro, int usuario_id) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.usuario_id = usuario_id;
	}
	
	public String getInfo() {
		return String.format("ID: %d\tRua: %s\tNúmero: %s\tComplemento: %s\tBairro: %s\tID Usuário: %s",
				id, rua, numero, complemento, bairro, usuario_id);
	}
}

public class EnderecoDAO {
	
	public static ArrayList<Endereco> getEnderecos(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.endereco";
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Endereco endereco = new Endereco(
					rs.getInt("id"), rs.getString("rua"), rs.getString("numero"), 
					rs.getString("complemento"), rs.getString("bairro"), rs.getInt("usuario_id")
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
	
	public static boolean inserirEndereco(Connection conexao, Endereco endereco) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.endereco (rua, numero, complemento, bairro, usuario_id) VALUES (?, ?, ?, ?, ?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, endereco.rua);
			st.setString(2, endereco.numero);
			st.setString(3, endereco.complemento);
			st.setString(4, endereco.bairro);
			st.setInt(5, endereco.usuario_id);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
	
	public static boolean alterarEndereco(Connection conexao, String rua, String numero, String complemento, String bairro, int usuarioId) throws SQLException {
		PreparedStatement st = null;
		String query = "UPDATE autotech.endereco SET rua = ?, numero = ?, complemento = ?, bairro = ? WHERE usuario_id = ?";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, rua);
			st.setString(2, numero);
			st.setString(3, complemento);
			st.setString(4, bairro);
			st.setInt(5, usuarioId);
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
