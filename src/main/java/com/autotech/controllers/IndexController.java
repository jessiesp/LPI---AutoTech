package com.autotech.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.autotech.daos.Database;
import com.autotech.daos.UsuarioDAO;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String Index(ModelMap model, HttpSession session) {
		
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		model.addAttribute("usuarioId", usuarioId);
		return "index";
	}

	@RequestMapping(value="/logout")
	public String Logout(HttpSession session) {
		session.removeAttribute("usuarioId");
		return "redirect:login";
	}
	
	@RequestMapping(value="/login")
	public String LoginForm(HttpSession session) {
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String LoginPost(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		String  email = request.getParameter("email"),
				senha = request.getParameter("password");
		int usuarioId = UsuarioDAO.getUsuarioId(conexao, email, senha);
		
		if(usuarioId == 0) {
			attributes.addFlashAttribute("mensagem", "Usuário ou senha inválidos!");
			return "redirect:/login";
		}
		else {
			session.setAttribute("usuarioId", usuarioId);
			return "redirect:/ordemServico";
		}
	}
}
