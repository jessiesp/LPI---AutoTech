package com.autotech.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.autotech.daos.CarroDAO;
import com.autotech.daos.CarroModeloDAO;
import com.autotech.daos.ClienteDAO;
import com.autotech.daos.Database;
import com.autotech.daos.EnderecoDAO;
import com.autotech.daos.UsuarioDAO;
import com.autotech.models.Carro;
import com.autotech.models.CarroModelo;
import com.autotech.models.Cliente;
import com.autotech.models.Endereco;
import com.autotech.models.Usuario;

@Controller
public class ClienteController {
	
	@RequestMapping("/registrarCliente")
	public String clienteForm(HttpSession session) {

		return "cliente-form";
	}

	@RequestMapping(value="/registrarCliente", method=RequestMethod.POST)
	public String clienteRegistrar(WebRequest request, RedirectAttributes attributes) throws SQLException {
		
		String email = request.getParameter("email"),
			   tipo = "cliente",
			   senha1 = request.getParameter("senha1"),
			   senha2 = request.getParameter("senha2"),
			   nome = request.getParameter("nome"),
			   cpf = request.getParameter("cpf");

		if (senha1.equals(senha2)) {
			Connection conexao = Database.getConexao();
			try {
				int usuarioId = UsuarioDAO.inserirUsuario(conexao, email, tipo, senha1);
				if (ClienteDAO.inserirCliente(conexao, nome, cpf, usuarioId)) {
					attributes.addFlashAttribute("mensagem", "Usuário incluso com sucesso!");
					return "redirect:/";
				} else {
					attributes.addFlashAttribute("mensagem", "Erro ao inserir cliente!");
				}
			} catch (SQLIntegrityConstraintViolationException e) {
				attributes.addFlashAttribute("mensagem", "E-mail já existe!");
			} finally {
				conexao.close();
			}
			
		} else {
			attributes.addFlashAttribute("mensagem", "Senha inválida!");
		}
		attributes.addFlashAttribute("nome", nome);
		attributes.addFlashAttribute("cpf", cpf);
		attributes.addFlashAttribute("email", email);
		return "redirect:/registrarCliente";
	}
	
	@RequestMapping("/perfilCliente")
	public String PerfilCliente(HttpSession session, ModelMap model) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		
		Usuario usuario = UsuarioDAO.getUsuario(conexao, usuarioId);
		
		if(!usuario.isCliente()) {
			return "redirect:/painel";
		}
		Cliente cliente = ClienteDAO.getClienteByUsuario(conexao, usuario.id);
		ArrayList<Endereco> enderecos = EnderecoDAO.getEnderecos(conexao, usuarioId);
		model.addAttribute("usuario", usuario);
		model.addAttribute("cliente", cliente);
		model.addAttribute("enderecos", enderecos);
		model.addAttribute("enderecos_lenght", enderecos.size());
		
		return "cliente-perfil";
	}
	
	@RequestMapping(value="/perfilCliente", method=RequestMethod.POST)
	public String AlterarDadosPessoais(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		Cliente cliente = ClienteDAO.getClienteByUsuario(conexao, usuarioId);
		try {
			ClienteDAO.alterarCliente(conexao, cliente.getId(), request.getParameter("nome"), request.getParameter("cpf"));
			attributes.addFlashAttribute("mensagem", "Dado alterado com sucesso!");
		} catch (SQLException e) {
			attributes.addFlashAttribute("mensagem", "Erro ao alterar dados!");
		}
		conexao.close(); 
		return "redirect:/perfilCliente";
	}

	@RequestMapping(value="/alterarSenha", method=RequestMethod.POST)
	public String AlterarSenha(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		String senha1 = request.getParameter("senha1"),
			   senha2 = request.getParameter("senha2");
		if(!senha1.equals(senha2)) {
			attributes.addFlashAttribute("mensagem", "Senhas não conferem!");
		}
		else if (senha1.length() <= 3) {
			attributes.addFlashAttribute("mensagem", "Insira uma senha com mais de 3 dígitos!");
		}
		else {	
			try {
				UsuarioDAO.alterarSenha(conexao, senha1, usuarioId);
				attributes.addFlashAttribute("mensagem", "Senha alterada com sucesso!");
			} catch (SQLException e) {
				attributes.addFlashAttribute("mensagem", "Erro ao alterar senha!");
			}
		}
		conexao.close(); 
		return "redirect:/perfilCliente";
	}
	
	
	@RequestMapping(value="/deletarTelefone", method=RequestMethod.POST)
	public String DeletarTelefone(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		try {
			String telefone = request.getParameter("telefone");
			UsuarioDAO.deletarTelefone(conexao, usuarioId, telefone);
			attributes.addFlashAttribute("mensagem", "Telefone removido com sucesso!");
		} catch (SQLException e) {
			attributes.addFlashAttribute("mensagem", "Erro ao remover telefone!");
		}
		conexao.close(); 
		return "redirect:/perfilCliente";
	}

	@RequestMapping(value="/adicionarTelefone", method=RequestMethod.POST)
	public String AdicionarTelefone(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		try {
			String telefone = request.getParameter("telefone");
			UsuarioDAO.inserirTelefone(conexao, usuarioId, telefone);
			attributes.addFlashAttribute("mensagem", "Telefone adicionado com sucesso!");
		} catch (SQLException e) {
			attributes.addFlashAttribute("mensagem", "Erro ao adicionar telefone!");
		}
		conexao.close(); 
		return "redirect:/perfilCliente";
	}
	
	@RequestMapping(value="/adicionarEndereco", method=RequestMethod.POST)
	public String AdicionarEndereco(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		try {
			String rua = request.getParameter("rua"),
				   numero = request.getParameter("numero"),
				   complemento = request.getParameter("complemento"),
				   bairro = request.getParameter("bairro"),
				   cidade = request.getParameter("cidade"),
				   uf = request.getParameter("uf");
			EnderecoDAO.inserirEndereco(conexao, usuarioId, rua, numero, complemento, bairro, cidade, uf);
			attributes.addFlashAttribute("mensagem", "Endereço adicionado com sucesso!");
		} catch (SQLException e) {
			attributes.addFlashAttribute("mensagem", "Erro ao adicionar endereço!");
		}
		conexao.close(); 
		return "redirect:/perfilCliente";
	}
	
	@RequestMapping(value="/deletarEndereco", method=RequestMethod.POST)
	public String deletarEndereco(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		
		if(usuarioId == null) {
			return "redirect:login";
		}
		
		int enderecoId = Integer.parseInt(request.getParameter("endereco"));
		
		if(EnderecoDAO.deletarEndereco(conexao, enderecoId, usuarioId)) {
			attributes.addFlashAttribute("mensagem", "Endereço removido com sucesso!");
		}
		else {
			attributes.addFlashAttribute("mensagem", "Erro ao remover endereço!");
		}
		
		return "redirect:/perfilCliente";
	}
	
	@RequestMapping("/veiculos")
	public String ListarVeiculos(HttpSession session, ModelMap model) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		
		ArrayList<Carro> carros = CarroDAO.getCarros(conexao, usuarioId);
		ArrayList<CarroModelo> modelos = CarroModeloDAO.getModelos(conexao);
		
		model.addAttribute("carros", carros);
		model.addAttribute("modelos", modelos);
		
		return "cliente-veiculos";
	}
	
	@RequestMapping(value="/veiculos", method=RequestMethod.POST)
	public String AdicionarVeiculo(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		String placa = request.getParameter("placa"),
			   ano = request.getParameter("ano"),
			   cor = request.getParameter("cor");
		int modeloId = Integer.parseInt(request.getParameter("modelo"));
		Cliente cliente = ClienteDAO.getClienteByUsuario(conexao, usuarioId);
		
		Carro carro = new Carro(placa, ano, cor, modeloId, cliente.getId());
		
		if(CarroDAO.inserirCarro(conexao, carro)) {
			attributes.addFlashAttribute("mensagem", "Veículo inserido com sucesso!");
		}else {
			attributes.addFlashAttribute("mensagem", "Erro ao inserir veículo!");
		}
		return "redirect:/veiculos";
	}
	
	@RequestMapping(value="/deletarVeiculo", method=RequestMethod.POST)
	public String DeletarVeiculo(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}

		int carroId = Integer.parseInt(request.getParameter("carroId"));
		
		if(CarroDAO.deletarCarro(conexao, carroId)) {
			attributes.addFlashAttribute("mensagem", "Veículo removido com sucesso!");
		}else {
			attributes.addFlashAttribute("mensagem", "Erro ao remover veículo!");
		}
		return "redirect:/veiculos";
	}
}
