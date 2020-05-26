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
		System.out.println("Conex�o efetuada com sucesso.");
		testarAplicacao(conexao);
		conexao.close(); 
	}
	
	public static void testarAplicacao(Connection conexao) throws SQLException {
		///////INSERIR USU�RIO///////
		/*Usuario user = new Usuario("mujica@gmail.com", "cliente", "123456");
		if (UsuarioDAO.inserirUsuario(conexao, user)) {
			System.out.println("Usu�rio criado.");
		} else {
			System.out.println("Erro ao inserir usu�rio.");
		}
		
		///////INSERIR TELEFONE///////
		if (UsuarioDAO.inserirTelefone(conexao, 32, "978456632")) {
			System.out.println("Telefone inserido.");
		} else {
			System.out.println("Erro ao inserir telefone.");
		}*/
		
		///////LISTAR USU�RIOS///////
		System.out.println("Listando usu�rios:");
		for (Usuario u : UsuarioDAO.getUsuarios(conexao)) {
			System.out.println(u.getInfo());
	    }
		
		///////INSERIR FUNCION�RIO///////
		/*Funcionario func = new Funcionario("Mujica", 32);
		if (FuncionarioDAO.inserirFuncionario(conexao, func)) {
			System.out.println("Dados do funcion�rio cadastrados.");
		} else {
			System.out.println("Erro ao inserir dados do funcion�rio.");
		}*/
		
		///////LISTAR FUNCION�RIOS///////
		System.out.println("Listando funcion�rios:");
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
		
		///////INSERIR ENDERE�O///////
		/*Endereco endereco = new Endereco("Santa Cruz", "100", null, "Barroca", 32);
		if (EnderecoDAO.inserirEndereco(conexao, endereco)) {
			System.out.println("Endere�o cadastrado.");
		} else {
			System.out.println("Erro ao inserir endere�o.");
		}*/
		
		///////LISTAR ENDERE�OS///////
		System.out.println("Listando endere�os:");
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
		
		///////INSERIR TIPO DE SERVI�O///////
		/*Servico servico = new Servico("Manuten��o preventiva", (float)100.0);
		if (ServicoDAO.inserirServico(conexao, servico)) {
			System.out.println("Servi�o cadastrado.");
		} else {
			System.out.println("Erro ao inserir servi�o.");
		}*/
		
		///////LISTAR TIPOS DE SERVI�O///////
		System.out.println("Listando servi�os:");	
		for (Servico s : ServicoDAO.getServicos(conexao)) {
			System.out.println(s.getInfo());
	    }
		
		///////CRIAR ORDEM DE SERVI�O///////
		/*OrdemServico ordemServico = new OrdemServico("Aberta", "2020-05-18 16:20", 1, 2);
		if (OrdemServicoDAO.inserirOrdemServico(conexao, ordemServico)) {
			System.out.println("Ordem de servi�o criada.");
		} else {
			System.out.println("Erro ao criar ordem de servi�o.");
		}*/
		
		///////ASSOCIAR UM SERVI�O A UMA ORDEM DE SERVI�O///////
		if (OrdemServicoDAO.associarServico(conexao, 3, 13)) {
			System.out.println("Servi�o associado.");
		} else {
			System.out.println("Erro ao associar servi�o.");
		}
		
		///////LISTAR ORDENS DE SERVI�O///////
		System.out.println("Listando ordens de servico:");
		for (OrdemServico os : OrdemServicoDAO.getOrdensServico(conexao)) {
			System.out.println(os.getInfo());
	    }	
	}
}
