package com.autotech.models;

public class Servico {
	
	public int id;
	public String nome;
	public float preco;
	
	public Servico(int id, String nome, float preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Servico(String nome, float preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public String getInfo() {
		return String.format(
				"ID: %d\tPlaca: %s\tPre�o: %f",
				id, nome, preco
		);
	}
}
