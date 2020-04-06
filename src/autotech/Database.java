package autotech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306?useTimezone=true&serverTimezone=UTC";
		String usuario = "root";
		String senha = "root";
		
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		System.out.println("Conexão efetuada com sucesso.");
		
		conexao.close(); 
	}
}
