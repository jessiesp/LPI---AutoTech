package com.autotech.models;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrdemServico {
	
	public int id, funcionarioId, carroId;
	public String status;
	public String data;
	public String comentarios;
	public ArrayList<Servico> servicos = new ArrayList<Servico>();
	
	public OrdemServico(int id, String status, String data, String comentarios, int funcionarioId, int carroId) {
		this.id = id;
		this.status = status;
		this.data = data;
		this.comentarios = comentarios;
		this.funcionarioId = funcionarioId;
		this.carroId = carroId;
	}
	
	public OrdemServico(String status, String data, String comentarios, int funcionarioId, int carroId) {
		this.status = status;
		this.data = data;
		this.comentarios = comentarios;
		this.funcionarioId = funcionarioId;
		this.carroId = carroId;
	}
	
	public String getServicos() {
		String 	servicos = "";
		String format;
		for (int i = 1; i <= this.servicos.size(); i++) {
			if(i == this.servicos.size()) {
				format = "%s";
			}
			else {
				format = "%s, ";
			}
			
			servicos += String.format(format, this.servicos.get(i - 1).nome);
		}
		return servicos;
	}
	
	public String getInfo() {
		return String.format(
				"ID: %d\tStatus: %s\tData: %s\tID Funcion�rio: %d\tID Carro: %d\tServi�os: %s",
				id, status, data, funcionarioId, carroId, this.getServicos()
		);
	}

	public void adicionarServico(Servico servico) {
		this.servicos.add(servico);
	}

	public int getId() {
		return id;
	}
	
	public Date getData() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = (Date)formatter.parse(this.data);
		return date;
	}
	
	public boolean hasServico(int servicoId) {
		for(int i = 0; i < this.servicos.size(); i++) {
			if(this.servicos.get(i).id == servicoId) return true;
		}
		return false;
	}

	public String getStatus() {
		return this.status;
	}
}
