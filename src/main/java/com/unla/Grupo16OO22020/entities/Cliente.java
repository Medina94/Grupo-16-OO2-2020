package com.unla.Grupo16OO22020.entities;

import javax.persistence.Entity;

import com.unla.Grupo16OO22020.models.ClienteModel;

@Entity
public class Cliente extends Persona{
	private int telefono;
	private boolean eliminado;
	
	public Cliente() {
		super();
	}

	public Cliente(int telefono, boolean eliminado) {
		this.telefono = telefono;
		this.eliminado = eliminado;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public Cliente build(ClienteModel modelo) {
		this.setId(modelo.getId());
		this.setNombre(modelo.getNombre());
		this.setApellido(modelo.getApellido());
		this.setFechaNacimiento(modelo.getFechaNacimiento());
		this.setMail(modelo.getMail());
		this.setDni(modelo.getDni());
		this.telefono = modelo.getTelefono();
		this.eliminado = modelo.isEliminado();
		return this;
	}
}
