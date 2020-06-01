package com.autotech.models;

public class Endereco {
	
	public int id, usuario_id;
	public String rua, numero, complemento, bairro;
	
	public Endereco(int id, String rua, String numero, String complemento, String bairro, int usuario_id) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.usuario_id = usuario_id;
	}
	
	public Endereco(String rua, String numero, String complemento, String bairro, int usuario_id) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.usuario_id = usuario_id;
	}
	
	public String getInfo() {
		return String.format("ID: %d\tRua: %s\tN�mero: %s\tComplemento: %s\tBairro: %s\tID Usu�rio: %s",
				id, rua, numero, complemento, bairro, usuario_id);
	}
}
