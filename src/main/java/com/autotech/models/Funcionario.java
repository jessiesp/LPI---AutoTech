package com.autotech.models;

public class Funcionario {
	
	public int id, usuario_id;
	public String nome;
	
	public Funcionario(int id, String nome, int usuario_id) {
		this.id = id;
		this.nome = nome;
		this.usuario_id = usuario_id;
	}
	
	public Funcionario(String nome, int usuario_id) {
		this.nome = nome;
		this.usuario_id = usuario_id;
	}
	
	public String getInfo() {
		return String.format(
			"ID: %d\tNome: %s\tID Usu�rio: %d", 
			id, nome, usuario_id
		);
	}
}
