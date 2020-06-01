package com.autotech.models;

public class CarroModelo {
	
	public int id, fabricante_id;
	public String nomeModelo;
	
	public CarroModelo(int id, String nomeModelo, int fabricante_id) {
		this.id = id;
		this.nomeModelo = nomeModelo;
		this.fabricante_id = fabricante_id;
	}
	
	public CarroModelo(String nomeModelo, int fabricante_id) {
		this.nomeModelo = nomeModelo;
		this.fabricante_id = fabricante_id;
	}
	
	public String getInfo() {
		return String.format("ID: %d\tModelo: %s\tID Usu�rio: %s",
				id, nomeModelo, fabricante_id);
	}
}
