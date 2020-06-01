package com.autotech.models;

public class Cliente {
	
	private int id, usuario_id;
	private String nome, cpf;
	
	public Cliente(int id, String nome, String cpf, int usuario_id) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.usuario_id = usuario_id;
	}
	
	public Cliente(String nome, String cpf, int usuario_id) {
		this.nome = nome;
		this.cpf = cpf;
		this.usuario_id = usuario_id;
	}
	
	public String getInfo() {
		return String.format(
				"ID: %d\tNome: %s\tCPF: %s\tID Usu�rio: %d", 
				id, nome, cpf, usuario_id
		);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public int getUsuarioId() {
		return this.usuario_id;
	}
	
	public int getId() {
		return this.id;
	}
}

