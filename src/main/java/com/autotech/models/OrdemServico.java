package com.autotech.models;

import java.util.ArrayList;

public class OrdemServico {
	
	public int id, funcionarioId, carroId;
	public String status;
	public String data;
	public ArrayList<Servico> servicos = new ArrayList<Servico>();
	
	public OrdemServico(int id, String status, String data, int funcionarioId, int carroId) {
		this.id = id;
		this.status = status;
		this.data = data;
		this.funcionarioId = funcionarioId;
		this.carroId = carroId;
	}
	
	public OrdemServico(String status, String data, int funcionarioId, int carroId) {
		this.status = status;
		this.data = data;
		this.funcionarioId = funcionarioId;
		this.carroId = carroId;
	}
	
	public String getInfo() {
		String 	servicos = "";
		for (Servico s : this.servicos) {
			servicos += String.format("%s - ", s.nome);
		}
		return String.format(
				"ID: %d\tStatus: %s\tData: %s\tID Funcion�rio: %d\tID Carro: %d\tServi�os: %s",
				id, status, data, funcionarioId, carroId, servicos
		);
	}

	public void adicionarServico(Servico servico) {
		this.servicos.add(servico);
	}
}
