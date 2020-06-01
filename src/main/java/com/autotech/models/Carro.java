package com.autotech.models;

public class Carro {
	
	public int id, carroModelo_id, cliente_id;
	public String placa, ano, cor;
	
	public Carro(int id, String placa, String ano, String cor, int carroModelo_id, int cliente_id) {
		this.carroModelo_id = carroModelo_id;
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
	
	public String getInfo() {
		return String.format(
				"ID: %d\tPlaca: %s\tAno: %s\tCor: %s\tID Modelo: %d\tID Cliente: %d",
				id, placa, ano, cor, carroModelo_id, cliente_id
		);
	}
}
