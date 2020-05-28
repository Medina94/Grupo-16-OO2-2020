package com.unla.Grupo16OO22020.models;

import java.time.LocalDate;

import com.unla.Grupo16OO22020.entities.Cliente;

public class ClienteModel extends PersonaModel{
	private int telefono;

	public ClienteModel() {}
	
	public ClienteModel(int id, int dni, String nombre, String apellido, LocalDate fechaNacimiento, String mail, int telefono) {
		super(id, dni, nombre, apellido, fechaNacimiento, mail);
		this.telefono = telefono;
	}
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public ClienteModel build(Cliente entidad) {
		this.setId(entidad.getId());
		this.setNombre(entidad.getNombre());
		this.setApellido(entidad.getApellido());
		this.setFechaNacimiento(entidad.getFechaNacimiento());
		this.setMail(entidad.getMail());
		this.setDni(entidad.getDni());
		this.telefono = entidad.getTelefono();
		return this;
	}
}
