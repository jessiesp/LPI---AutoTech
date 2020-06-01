package com.autotech.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.autotech.models.Carro;
import com.autotech.models.CarroModelo;
import com.autotech.models.Cliente;
import com.autotech.models.Endereco;
import com.autotech.models.Fabricante;
import com.autotech.models.Funcionario;
import com.autotech.models.OrdemServico;
import com.autotech.models.Servico;
import com.autotech.models.Usuario;

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
		///////INSERIR USU�RIO///////
		/*Usuario user = new Usuario("mujica@gmail.com", "cliente", "123456");
		if (UsuarioDAO.inserirUsuario(conexao, "mujica@gmail.com", "cliente", "123456")) {
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
		
		///////DELETAR USU�RIO///////
		/*if (UsuarioDAO.deletarUsuario(conexao, 37)) {
			System.out.println("Usu�rio deletado.");
		} else {
			System.out.println("Erro ao deletar usu�rio.");
		}*/
		
		///////ALTERAR DADOS DO USU�RIO///////
		/*if(UsuarioDAO.alterarUsuario(conexao, "juju@sachemail.com", "Cliente", "123456", "978456325", 3)) {
			System.out.println("Dados do usu�rio alterados.");
		} else {
			System.out.println("Erro ao alterar dados do usu�rio.");
		}*/
		
		///////LISTAR USU�RIOS///////
		System.out.println("Listando usuários:");
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
		
		///////DELETAR FUNCION�RIO///////
		/*if (FuncionarioDAO.deletarFuncionario(conexao, 3)) {
			System.out.println("Funcion�rio deletado.");
		} else {
			System.out.println("Erro ao deletar funcion�rio.");
		}*/
		
		///////ALTERAR DADOS DO FUNCION�RIO///////
		/*if(FuncionarioDAO.alterarFuncionario(conexao, "Janis Fucking Joplin", 2)) {
			System.out.println("Dados do funcion�rio alterados.");
		} else {
			System.out.println("Erro ao alterar dados do funcion�rio.");
		}*/
		
		///////LISTAR FUNCION�RIOS///////
		System.out.println("Listando funcion�rios:");
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
		
		///////INSERIR ENDERE�O///////
		/*Endereco endereco = new Endereco("Santa Cruz", "100", null, "Barroca", 32);
		if (EnderecoDAO.inserirEndereco(conexao, endereco)) {
			System.out.println("Endere�o cadastrado.");
		} else {
			System.out.println("Erro ao inserir endere�o.");
		}*/
		
		///////ALTERAR ENDERE�O///////
		/*if(EnderecoDAO.alterarEndereco(conexao, "Amur", "200", null, "Bet�nia", 32)) {
			System.out.println("Endere�o alterado.");
		} else {
			System.out.println("Erro ao alterar endere�o.");
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
			System.out.println("Ve�culo deletado.");
		} else {
			System.out.println("Erro ao deletar ve�culo.");
		}*/
		
		///////INSERIR TIPO DE SERVI�O///////
		/*Servico servico = new Servico("Manuten��o preventiva", (float)100.0);
		if (ServicoDAO.inserirServico(conexao, servico)) {
			System.out.println("Servi�o cadastrado.");
		} else {
			System.out.println("Erro ao inserir servi�o.");
		}*/
		
		///////DELETAR SERVI�O///////
		/*if (ServicoDAO.deletarServico(conexao, 7)) {
			System.out.println("Servi�o deletado.");
		} else {
			System.out.println("Erro ao deletar servi�o.");
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
		/*if (OrdemServicoDAO.associarServico(conexao, 3, 13)) {
			System.out.println("Servi�o associado.");
		} else {
			System.out.println("Erro ao associar servi�o.");
		}*/
		
		///////DELETAR ORDEM DE SERVI�O///////
		/*if (OrdemServicoDAO.deletarOrdemServico(conexao, 13)) {
			System.out.println("Ordem de servi�o deletada.");
		} else {
			System.out.println("Erro ao deletar ordem de servi�o.");
		}*/
		
		///////ALTERAR ORDEM DE SERVI�O///////
		/*if(OrdemServicoDAO.alterarOrdemServico(conexao, "Fechada", "2020-05-29 22:53", 2, 14)) {
			System.out.println("Ordem de servi�o alterada.");
		} else {
			System.out.println("Erro ao alterar ordem de servi�o.");
		}*/
		
		///////LISTAR ORDENS DE SERVI�O///////
		System.out.println("Listando ordens de servico:");
		for (OrdemServico os : OrdemServicoDAO.getOrdensServico(conexao)) {
			System.out.println(os.getInfo());
	    }	
	}
}
