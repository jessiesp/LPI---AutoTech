package com.autotech.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.autotech.daos.CarroDAO;
import com.autotech.daos.ClienteDAO;
import com.autotech.daos.Database;
import com.autotech.daos.OrdemServicoDAO;
import com.autotech.daos.ServicoDAO;
import com.autotech.daos.UsuarioDAO;
import com.autotech.models.Carro;
import com.autotech.models.Cliente;
import com.autotech.models.OrdemServico;

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
		model.addAttribute("carros", CarroDAO.getCarros(conexao, usuarioId));
		conexao.close(); 
		
		return "cliente-ordemServico";
	}
	
	@RequestMapping(value="/ordemServico", method=RequestMethod.POST)
	public String addOrdemServico(HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		
		if(usuarioId == null) {
			return "redirect:login";
		}
		String[] servicos = request.getParameterValues("servicos");
		String data = request.getParameter("data"),
			   status = request.getParameter("status"),
			   comentarios = request.getParameter("comentarios");
		int carroId = Integer.parseInt(request.getParameter("carroId"));
		
		try {
			int idOrdemServico = OrdemServicoDAO.inserirOrdemServico(conexao, status, data, comentarios, carroId);
			for(int i = 0; i < servicos.length; i++) {
				int servicoId = Integer.parseInt(servicos[i]);
				 OrdemServicoDAO.associarServico(conexao, servicoId, idOrdemServico);
			}
			attributes.addFlashAttribute("mensagem", "Serviço solicitado com sucesso!");
		} catch (SQLException e ) {
			attributes.addFlashAttribute("mensagem", "Erro ao solicitar ordem de serviço!");
		}
		
		return "redirect:/listarOrdensServico";
	}
	
	@RequestMapping("/listarOrdensServico")
	public String ListarOrdensServico(ModelMap model, HttpSession session) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		
		if(usuarioId == null) {
			return "redirect:login";
		}
		model.addAttribute("carros", CarroDAO.getCarrosAndServicos(conexao, usuarioId));
		conexao.close(); 
		return "listarOrdensServico";
	}
	
	@RequestMapping("/deletarOrdemServico")
	public String deletarOrdemServico(@RequestParam int id, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		try {
			OrdemServicoDAO.deletarOrdemServico(conexao, id);
			attributes.addFlashAttribute("mensagem", "Ordem de serviço removida com sucesso!");
		} catch (SQLException e) {
			attributes.addFlashAttribute("mensagem", "Erro ao remover ordem de serviço!");
		}
		conexao.close(); 
		return "redirect:/listarOrdensServico";
	}
	
	@RequestMapping(value="/alterarOrdemServico")
	public String alterarOrdemServico(@RequestParam int id, HttpSession session, ModelMap model) throws SQLException {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if(usuarioId == null) {
			return "redirect:login";
		}
		
		Connection conexao = Database.getConexao();
		model.addAttribute("servicos", ServicoDAO.getServicos(conexao));
		model.addAttribute("carros", CarroDAO.getCarros(conexao, usuarioId));
		model.addAttribute("ordemServico", OrdemServicoDAO.getOrdemServico(conexao, id));
		conexao.close(); 
		
		return "cliente-alterarOrdemServico";
	}
	
	@RequestMapping(value="/alterarOrdemServico", method=RequestMethod.POST)
	public String alterarOrdemServico(@RequestParam int id, HttpSession session, WebRequest request, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		
		if(usuarioId == null) {
			return "redirect:login";
		}
		String[] servicos = request.getParameterValues("servicos");
		String data = request.getParameter("data"),
			   comentarios = request.getParameter("comentarios");
		int carroId = Integer.parseInt(request.getParameter("carroId"));
		
		if(OrdemServicoDAO.alterarOrdemServico(conexao, data, comentarios, carroId, id)) {
			OrdemServicoDAO.desassociarServicos(conexao, id);
			for(int i = 0; i < servicos.length; i++) {
				int servicoId = Integer.parseInt(servicos[i]);
				 OrdemServicoDAO.associarServico(conexao, servicoId, id);
			}
			attributes.addFlashAttribute("mensagem", "Ordem de serviço editada com sucesso!");
		}
		else {
			attributes.addFlashAttribute("mensagem", "Erro ao editar ordem de serviço!");
		}
		
		return "redirect:/listarOrdensServico";
	}
}

	
