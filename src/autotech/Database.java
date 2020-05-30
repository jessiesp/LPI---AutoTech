package autotech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	public static Connection getConexao() throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306?useTimezone=true&serverTimezone=UTC";
		String usuario = "root";
		String senha = "root";
		
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		return conexao;
	}

	public static void main(String[] args) throws SQLException {
		
		Connection conexao;
		conexao = Database.getConexao();
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
		
		///////DELETAR USUÁRIO///////
		/*if (UsuarioDAO.deletarUsuario(conexao, 37)) {
			System.out.println("Usuário deletado.");
		} else {
			System.out.println("Erro ao deletar usuário.");
		}*/
		
		///////ALTERAR DADOS DO USUÁRIO///////
		/*if(UsuarioDAO.alterarUsuario(conexao, "juju@sachemail.com", "Cliente", "123456", "978456325", 3)) {
			System.out.println("Dados do usuário alterados.");
		} else {
			System.out.println("Erro ao alterar dados do usuário.");
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
		
		///////DELETAR FUNCIONÁRIO///////
		/*if (FuncionarioDAO.deletarFuncionario(conexao, 3)) {
			System.out.println("Funcionário deletado.");
		} else {
			System.out.println("Erro ao deletar funcionário.");
		}*/
		
		///////ALTERAR DADOS DO FUNCIONÁRIO///////
		/*if(FuncionarioDAO.alterarFuncionario(conexao, "Janis Fucking Joplin", 2)) {
			System.out.println("Dados do funcionário alterados.");
		} else {
			System.out.println("Erro ao alterar dados do funcionário.");
		}*/
		
		///////LISTAR FUNCIONÁRIOS///////
		System.out.println("Listando funcionários:");
		for (Funcionario f : FuncionarioDAO.getFuncionarios(conexao)) {
			System.out.println(f.getInfo());
	    }
		
		///////INSERIR CLIENTE///////
		/*Cliente cliente = new Cliente("Ana", "96587456321", 38);
		if (ClienteDAO.inserirCliente(conexao, cliente)) {
			System.out.println("Dados do cliente cadastrados.");
		} else {
			System.out.println("Erro ao inserir dados do cliente.");
		}*/
		
		///////DELETAR CLIENTES///////
		/*if (ClienteDAO.deletarCliente(conexao, 4)) {
			System.out.println("Cliente deletado.");
		} else {
			System.out.println("Erro ao deletar cliente.");
		}*/
		
		///////ALTERAR CLIENTES///////
		/*if(ClienteDAO.alterarCliente(conexao, 1, "Ricardo", "06475206105")) {
			System.out.println("Dados do cliente alterados.");
		} else {
			System.out.println("Erro ao alterar dados.");
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
		
		///////ALTERAR ENDEREÇO///////
		/*if(EnderecoDAO.alterarEndereco(conexao, "Amur", "200", null, "Betânia", 32)) {
			System.out.println("Endereço alterado.");
		} else {
			System.out.println("Erro ao alterar endereço.");
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
		}
		*/
		///////LISTAR MODELOS DE CARRO///////
		System.out.println("Listando modelos:");
		for (CarroModelo cm : CarroModeloDAO.getModelos(conexao)) {
			System.out.println(cm.getInfo());
	    }
		
		///////DELETAR MODELO DO CARRO///////
		/*if (CarroModeloDAO.deletarModelo(conexao, 7)) {
			System.out.println("Modelo deletado.");
		} else {
			System.out.println("Erro ao deletar modelo.");
		}*/
		
		///////INSERIR CARRO///////
		/*Carro carro = new Carro("OMG6545", "2015", "Vermelho", 1, 1);
		if (CarroDAO.inserirCarro(conexao, carro)) {
			System.out.println("Carro cadastrado.");
		} else {
			System.out.println("Erro ao inserir carro.");
		}*/
		
		///////LISTAR CARROS///////
		System.out.println("Listando carros:");
		for (Carro c : CarroDAO.getCarros(conexao)) {
			System.out.println(c.getInfo());
	    }
		
		///////DELETAR CARRO///////
		/*if (CarroDAO.deletarCarro(conexao, 3)) {
			System.out.println("Veículo deletado.");
		} else {
			System.out.println("Erro ao deletar veículo.");
		}*/
		
		///////INSERIR TIPO DE SERVIÇO///////
		/*Servico servico = new Servico("Manutenção preventiva", (float)100.0);
		if (ServicoDAO.inserirServico(conexao, servico)) {
			System.out.println("Serviço cadastrado.");
		} else {
			System.out.println("Erro ao inserir serviço.");
		}*/
		
		///////DELETAR SERVIÇO///////
		/*if (ServicoDAO.deletarServico(conexao, 7)) {
			System.out.println("Serviço deletado.");
		} else {
			System.out.println("Erro ao deletar serviço.");
		}*/
		
		///////LISTAR TIPOS DE SERVIÇO///////
		System.out.println("Listando serviços:");	
		for (Servico s : ServicoDAO.getServicos(conexao)) {
			System.out.println(s.getInfo());
	    }
		
		///////CRIAR ORDEM DE SERVIÇO///////
		/*OrdemServico ordemServico = new OrdemServico("Aberta", "2020-05-18 16:20", 1, 2);
		if (OrdemServicoDAO.inserirOrdemServico(conexao, ordemServico)) {
			System.out.println("Ordem de serviço criada.");
		} else {
			System.out.println("Erro ao criar ordem de serviço.");
		}*/
		
		///////ASSOCIAR UM SERVIÇO A UMA ORDEM DE SERVIÇO///////
		/*if (OrdemServicoDAO.associarServico(conexao, 3, 13)) {
			System.out.println("Serviço associado.");
		} else {
			System.out.println("Erro ao associar serviço.");
		}*/
		
		///////DELETAR ORDEM DE SERVIÇO///////
		/*if (OrdemServicoDAO.deletarOrdemServico(conexao, 13)) {
			System.out.println("Ordem de serviço deletada.");
		} else {
			System.out.println("Erro ao deletar ordem de serviço.");
		}*/
		
		///////ALTERAR ORDEM DE SERVIÇO///////
		/*if(OrdemServicoDAO.alterarOrdemServico(conexao, "Fechada", "2020-05-29 22:53", 2, 14)) {
			System.out.println("Ordem de serviço alterada.");
		} else {
			System.out.println("Erro ao alterar ordem de serviço.");
		}*/
		
		///////LISTAR ORDENS DE SERVIÇO///////
		System.out.println("Listando ordens de servico:");
		for (OrdemServico os : OrdemServicoDAO.getOrdensServico(conexao)) {
			System.out.println(os.getInfo());
	    }	
	}
}
