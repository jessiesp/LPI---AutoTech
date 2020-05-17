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
		testarAplicacao(conexao);
		conexao.close(); 
	}
	
	public static void testarAplicacao(Connection conexao) throws SQLException {
		///////INSERIR USUÁRIO///////
		/*Usuario user = new Usuario("mujica@gmail.com", "cliente", "123456");
		if (UsuarioDAO.inserirUsuario(conexao, user)) {
			System.out.println("Usuário criado.");
		} else {
			System.out.println("Erro ao inserir usuário.");
		}
		
		///////INSERIR TELEFONE///////
		if (UsuarioDAO.inserirTelefone(conexao, 32, "978456632")) {
			System.out.println("Telefone inserido.");
		} else {
			System.out.println("Erro ao inserir telefone.");
		}*/
		
		///////LISTAR USUÁRIOS///////
		System.out.println("Listando usuários:");
		for (Usuario u : UsuarioDAO.getUsuarios(conexao)) {
			System.out.println(u.getInfo());
	    }
		
		///////INSERIR FUNCIONÁRIO///////
		/*Funcionario func = new Funcionario("Mujica", 32);
		if (FuncionarioDAO.inserirFuncionario(conexao, func)) {
			System.out.println("Dados do funcionário cadastrados.");
		} else {
			System.out.println("Erro ao inserir dados do funcionário.");
		}*/
		
		///////LISTAR FUNCIONÁRIOS///////
		System.out.println("Listando funcionários:");
		for (Funcionario f : FuncionarioDAO.getFuncionarios(conexao)) {
			System.out.println(f.getInfo());
	    }
		
		///////INSERIR CLIENTE///////
		/*Cliente cliente = new Cliente("Mujica", "12365478965", 32);
		if (ClienteDAO.inserirCliente(conexao, cliente)) {
			System.out.println("Dados do cliente cadastrados.");
		} else {
			System.out.println("Erro ao inserir dados do cliente.");
		}*/
		
		///////LISTAR CLIENTES///////
		System.out.println("Listando clientes:");
		for (Cliente c : ClienteDAO.getClientes(conexao)) {
			System.out.println(c.getInfo());
	    }
		
		///////INSERIR ENDEREÇO///////
		/*Endereco endereco = new Endereco("Santa Cruz", "100", null, "Barroca", 32);
		if (EnderecoDAO.inserirEndereco(conexao, endereco)) {
			System.out.println("Endereço cadastrado.");
		} else {
			System.out.println("Erro ao inserir endereço.");
		}*/
		
		///////LISTAR ENDEREÇOS///////
		System.out.println("Listando endereços:");
		for (Endereco e : EnderecoDAO.getEnderecos(conexao)) {
			System.out.println(e.getInfo());
	    }
		
		///////INSERIR FABRICANTE DE CARROS///////
		/*Fabricante fabricante = new Fabricante("Toyota");
		if (FabricanteDAO.inserirFabricante(conexao, fabricante)) {
			System.out.println("Fabricante cadastrado.");
		} else {
			System.out.println("Erro ao inserir fabricante.");
		}*/
		
		///////LISTAR FABRICANTES///////
		System.out.println("Listando fabricantes:");
		for (Fabricante f : FabricanteDAO.getFabricantes(conexao)) {
			System.out.println(f.getInfo());
	    }
		
		///////INSERIR MODELO DO CARRO///////
		/*CarroModelo modelo = new CarroModelo("Etios", 6);
		if (CarroModeloDAO.inserirModelo(conexao, modelo)) {
			System.out.println("Modelo cadastrado.");
		} else {
			System.out.println("Erro ao inserir modelo.");
		}*/
		
		///////LISTAR MODELOS DE CARRO///////
		System.out.println("Listando modelos:");
		for (CarroModelo cm : CarroModeloDAO.getModelos(conexao)) {
			System.out.println(cm.getInfo());
	    }
	}
}
