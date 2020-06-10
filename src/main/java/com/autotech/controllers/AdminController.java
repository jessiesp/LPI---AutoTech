package com.autotech.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.autotech.daos.ClienteDAO;
import com.autotech.daos.Database;
import com.autotech.daos.UsuarioDAO;
import com.autotech.models.Cliente;

@Controller
public class AdminController {
	
	@RequestMapping("/listarClientes")
	public String ListarClientes(ModelMap model) throws SQLException {
		Connection conexao = Database.getConexao();
		model.addAttribute("clientes", ClienteDAO.getClientes(conexao));
		conexao.close(); 
		return "clientes-lista";
	}
	
	@RequestMapping("/deletarCliente")
	public String deletarCliente(@RequestParam int id, RedirectAttributes attributes) throws SQLException {
		Connection conexao = Database.getConexao();
		Cliente cliente = ClienteDAO.getCliente(conexao, id);
		try {
			ClienteDAO.deletarCliente(conexao, id);
			UsuarioDAO.deletarUsuario(conexao, cliente.getUsuarioId());
			attributes.addFlashAttribute("mensagem", "Cliente removido com sucesso!");
		} catch (SQLException e) {
			attributes.addFlashAttribute("mensagem", "Erro ao remover cliente!");
		}
		conexao.close(); 
		return "redirect:/listarClientes";
	}
}


