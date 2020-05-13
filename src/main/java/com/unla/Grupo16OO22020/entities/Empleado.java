package com.unla.Grupo16OO22020.entities;

import javax.persistence.Entity;

import com.unla.Grupo16OO22020.models.EmpleadoModel;

@Entity
public class Empleado extends Persona{
	private int sueldo;
	private boolean esGerente;
	private int idLocal;

	public Empleado() {
		super();
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public boolean isEsGerente() {
		return esGerente;
	}

	public void setEsGerente(boolean esGerente) {
		this.esGerente = esGerente;
	}
	
	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public Empleado build(EmpleadoModel modelo) {
		this.setId(modelo.getId());
		this.setNombre(modelo.getNombre());
		this.setApellido(modelo.getApellido());
		this.setFechaNacimiento(modelo.getFechaNacimiento());
		this.setMail(modelo.getMail());
		this.sueldo = modelo.getSueldo();
		this.setEsGerente(modelo.isEsGerente());
		this.setIdLocal(modelo.getIdLocal());
		return this;
	}
}
