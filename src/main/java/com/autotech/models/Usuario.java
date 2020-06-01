package com.autotech.models;

import java.util.ArrayList;

public class Usuario {
	public int id;
	public String email, tipo, password;
	public ArrayList<String> telefones = new ArrayList<String>();
	
	public Usuario(int id, String email, String tipo) {
		this.id = id;
		this.email = email;
		this.tipo = tipo;
	}
	
	public Usuario(String email, String tipo, String password) {
		this.email = email;
		this.tipo = tipo;
		this.password = password;
	}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getTelefones() {
    	String tels = "";
		for (String t : this.telefones) {
			tels += String.format("%s, ", t);
		}
		return tels;
    }

	public String getInfo() {
		return String.format(
			"ID: %d\tEmail: %s\tTipo: %s\tTelefones: %s",
			id, email, tipo, this.getTelefones()
		);
	}
	
	public void adicionarTelefone(String telefone) {
		this.telefones.add(telefone);
	}
}
