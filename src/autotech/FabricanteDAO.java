package autotech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Fabricante {
	
	public int id;
	public String nome;
	
	public Fabricante(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Fabricante(String nome) {
		this.nome = nome;
	}
	
	public String getInfo() {
		return String.format("ID: %d\tNome: %s",
				id, nome);
	}
}

public class FabricanteDAO {
	
	public static ArrayList<Fabricante> getFabricantes(Connection conexao) throws SQLException {
		Statement st = null;
		String query = "SELECT * FROM autotech.fabricante";
		ArrayList<Fabricante> fabricantes = new ArrayList<Fabricante>();
		
		try {
			st = conexao.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Fabricante fabricante = new Fabricante(
					rs.getInt("id"), rs.getString("nome")
				);
				fabricantes.add(fabricante);
			}
		}
			catch (SQLException e ) {
				System.out.println("Erro! " + e);
		    } 
			finally {
		        if (st != null) st.close(); 
		    }	
			return fabricantes;	
		}
	
	public static boolean inserirFabricante(Connection conexao, Fabricante fabricante) throws SQLException {
		PreparedStatement st = null;
		String query = "INSERT INTO autotech.fabricante (nome) VALUES (?)";
		
		try {
			st = conexao.prepareStatement(query);
			st.setString(1, fabricante.nome);
			st.execute();
			return true;
		} 
		catch (SQLException e ) {
			return false;
	    }
	}
}
