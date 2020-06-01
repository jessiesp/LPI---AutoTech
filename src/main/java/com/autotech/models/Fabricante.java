package com.autotech.models;

public class Fabricante {
	
	public int id;
	public String nome;
	
	public Fabricante(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Fabricante(String nome) {
		this.nome = nome;
	}
	
	public String getInfo() {
		return String.format("ID: %d\tNome: %s",
				id, nome);
	}
}

