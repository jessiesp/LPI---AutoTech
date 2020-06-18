package com.autotech.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.autotech.daos.Database;
import com.autotech.daos.OrdemServicoDAO;
import com.autotech.daos.UsuarioDAO;
import com.autotech.models.Usuario;

@Controller
public class FuncionarioController {
	
	@RequestMapping("/painel")
	public String painelOrdensServico(HttpSession session, ModelMap model) throws SQLException {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		System.out.println(usuarioId);
		if(usuarioId == null) {
			return "redirect:login";
		}
		Connection conexao = Database.getConexao();
		Usuario usuario = UsuarioDAO.getUsuario(conexao, usuarioId);
		
		if(!usuario.isFuncionario()) {
			return "redirect:/";
		}
		model.addAttribute("ordensEmAnalise", OrdemServicoDAO.getOrdensServicoByStatus(conexao, "emAnalise"));
		model.addAttribute("ordensEmAndamento", OrdemServicoDAO.getOrdensServicoByStatus(conexao, "emAndamento"));
		model.addAttribute("ordensConcluido", OrdemServicoDAO.getOrdensServicoByStatus(conexao, "concluido"));

		return "funcionario-painelOrdens";
	}
	
	@RequestMapping(value="/alterarStatusOrdemServico")
	public String alterarOrdemServico(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		
		Connection conexao = Database.getConexao();
		Usuario usuario = UsuarioDAO.getUsuario(conexao, usuarioId);
		
		if(!usuario.isFuncionario()) {
			return "redirect:/";
		}
		
		String status = request.getParameter("status");
		int ordemServicoId = Integer.parseInt(request.getParameter("ordemServicoId"));
		
		if (OrdemServicoDAO.alterarStatus(conexao, status, ordemServicoId)) {
			attributes.addFlashAttribute("mensagem", String.format("Status alterado para %s com sucesso!", status));
		}
		else {
			attributes.addFlashAttribute("mensagem", "Erro ao alterar status!");
		}
		conexao.close(); 
		
		return "redirect:/painel";
	}
}
