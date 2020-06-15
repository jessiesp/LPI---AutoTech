package com.autotech.models;

import java.util.ArrayList;

public class Usuario {
	public int id;
	public String email, tipo, password;
	public ArrayList<String> telefones = new ArrayList<String>();
	public static final String CLIENTE = "cliente", FUNCIONARIO = "funcionario";
	
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
    
    public String getTelefonesStr() {
    	String tels = "";
		for (String t : this.telefones) {
			tels += String.format("%s, ", t);
		}
		return tels;
    }
    
    public ArrayList<String> getTelefones() {
		return this.telefones;
    }

	public String getInfo() {
		return String.format(
			"ID: %d\tEmail: %s\tTipo: %s\tTelefones: %s",
			id, email, tipo, this.getTelefonesStr()
		);
	}
	
	public void adicionarTelefone(String telefone) {
		this.telefones.add(telefone);
	}
	
	public boolean isCliente() {
		return this.tipo.equals(CLIENTE);
	}
	
	public boolean isFuncionario() {
		return this.tipo.equals(FUNCIONARIO);
	}
}
