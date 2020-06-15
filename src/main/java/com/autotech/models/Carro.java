package com.autotech.models;

import java.util.ArrayList;

public class Carro {
	
	public int id, carroModelo_id, cliente_id;
	public String placa, ano, cor, nomeModelo, nomeFabricante;
	private ArrayList<OrdemServico> ordensServico;
	
	public Carro(int id, String placa, String ano, String cor, int carroModelo_id, int cliente_id) {
		this.id = id;
		this.placa = placa;
		this.ano = ano;
		this.cor = cor;
		this.carroModelo_id = carroModelo_id;
		this.cliente_id = cliente_id;
	}
	
	public Carro(String placa, String ano, String cor, int carroModelo_id, int cliente_id) {
		this.placa = placa;
		this.ano = ano;
		this.cor = cor;
		this.carroModelo_id = carroModelo_id;
		this.cliente_id = cliente_id;
	}
	
	public Carro(int id, String placa, String ano, String cor, String nomeModelo, String nomeFabricante) {
		this.id = id;
		this.placa = placa;
		this.ano = ano;
		this.cor = cor;
		this.nomeModelo = nomeModelo;
		this.nomeFabricante = nomeFabricante;
	}
	public String getInfo() {
		return String.format(
				"ID: %d\tPlaca: %s\tAno: %s\tCor: %s\tID Modelo: %d\tID Cliente: %d",
				id, placa, ano, cor, carroModelo_id, cliente_id
		);
	}
	
	public String toString() {
		return String.format(
			"%s %s %s", placa, ano, cor
		);
	}
	
	public String toFullString() {
		return String.format(
			"%s %s %s %s %s", placa, nomeFabricante, nomeModelo, cor, ano
		);
	}
	
	public void setOrdensServico(ArrayList<OrdemServico> ordensServico) {
		this.ordensServico = ordensServico;
	}
	
	public ArrayList<OrdemServico> getOrdensServico() {
		return this.ordensServico;
	}
}
