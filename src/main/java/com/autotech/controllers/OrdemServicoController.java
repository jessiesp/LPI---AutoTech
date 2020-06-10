package com.autotech.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.autotech.daos.ClienteDAO;
import com.autotech.daos.Database;
import com.autotech.daos.ServicoDAO;

@Controller
public class OrdemServicoController {

	@RequestMapping("/ordemServico")
	public String OrdemServico(HttpSession session, WebRequest request, ModelMap model) throws SQLException {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		
		Connection conexao = Database.getConexao();
		model.addAttribute("servicos", ServicoDAO.getServicos(conexao));
		conexao.close(); 
		
		return "cliente-ordemServico";
	}
	
	@RequestMapping(value="/ordemServico", method=RequestMethod.POST)
	public String addOrdemServico(HttpSession session, WebRequest request) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		String[] servicos = request.getParameterValues("servicos");
		String data = request.getParameter("data"),
			   comentarios = request.getParameter("comentarios");
		
		for(int i = 0; i < servicos.length; i++) {
			System.out.println(servicos[i]);
		}
		
		return "redirect:/ordemServico";
	}
}
