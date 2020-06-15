package com.autotech.models;

public class CarroModelo {
	
	public int id, fabricante_id;
	public String nomeModelo, nomeFabricante;
	
	public CarroModelo(int id, String nomeModelo, int fabricante_id) {
		this.id = id;
		this.nomeModelo = nomeModelo;
		this.fabricante_id = fabricante_id;
	}
	
	public CarroModelo(String nomeModelo, int fabricante_id) {
		this.nomeModelo = nomeModelo;
		this.fabricante_id = fabricante_id;
	}
	
	public CarroModelo(int id, String nomeModelo, String nomeFabricante) {
		this.id = id;
		this.nomeModelo = nomeModelo;
		this.nomeFabricante = nomeFabricante;
	}
	
	public String getInfo() {
		return String.format("ID: %d\tModelo: %s\tID Usu�rio: %s",
				id, nomeModelo, fabricante_id);
	}
	
	public String toString() {
		return String.format("%s, %s", nomeModelo, nomeFabricante);
	}
}
