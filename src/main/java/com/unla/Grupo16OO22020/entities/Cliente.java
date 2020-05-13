package com.unla.Grupo16OO22020.entities;

import javax.persistence.Entity;

import com.unla.Grupo16OO22020.models.ClienteModel;

@Entity
public class Cliente extends Persona{
	private int telefono;
	
	public Cliente() {
		super();
	}

	public Cliente(int telefono) {
		this.telefono = telefono;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public Cliente build(ClienteModel modelo) {
		this.setId(modelo.getId());
		this.setNombre(modelo.getNombre());
		this.setApellido(modelo.getApellido());
		this.setFechaNacimiento(modelo.getFechaNacimiento());
		this.setMail(modelo.getMail());
		this.telefono = modelo.getTelefono();
		return this;
	}
}
