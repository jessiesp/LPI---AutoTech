package com.autotech.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.autotech.daos.ClienteDAO;
import com.autotech.daos.Database;
import com.autotech.daos.UsuarioDAO;

@Controller
public class ClienteController {
	@RequestMapping("/registrarCliente")
	public String clienteForm(HttpSession session) {

		return "cliente-form";
	}

	@RequestMapping(value="/registrarCliente", method=RequestMethod.POST)
	public String clienteRegistrar(
			WebRequest request,
			RedirectAttributes attributes) throws SQLException {
		
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
}
