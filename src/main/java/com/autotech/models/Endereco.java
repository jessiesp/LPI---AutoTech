package com.autotech.models;

public class Endereco {
	
	public int id, usuario_id;
	public String rua, numero, complemento, bairro, cidade, uf;
	
	public Endereco(int id, String rua, String numero, String complemento, String bairro, String cidade, String uf, int usuario_id) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.usuario_id = usuario_id;
		this.cidade = cidade;
		this.uf = uf;
	}
	
	public Endereco(String rua, String numero, String complemento, String bairro, String cidade, String uf, int usuario_id) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.usuario_id = usuario_id;
		this.cidade = cidade;
		this.uf = uf;
	}
	
	public String getInfo() {
		return String.format("ID: %d\tRua: %s\tN�mero: %s\tComplemento: %s\tBairro: %s\tID Usu�rio: %s",
				id, rua, numero, complemento, bairro, usuario_id);
	}
	
	public String toString() {
		return String.format("%s, %s - %s - %s - %s/%s", rua, numero, complemento, bairro, cidade, uf); 
	}
} 	
