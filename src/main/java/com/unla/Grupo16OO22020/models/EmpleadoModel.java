package com.unla.Grupo16OO22020.models;

import com.unla.Grupo16OO22020.entities.Empleado;

public class EmpleadoModel extends PersonaModel{
	private int sueldo;
	private boolean esGerente;
	private int idLocal;
	
	public EmpleadoModel() {}
	
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

	public EmpleadoModel build(Empleado entidad) {
		this.setId(entidad.getId());
		this.setNombre(entidad.getNombre());
		this.setApellido(entidad.getApellido());
		this.setFechaNacimiento(entidad.getFechaNacimiento());
		this.setMail(entidad.getMail());
		this.sueldo = entidad.getSueldo();
		this.setEsGerente(entidad.isEsGerente());
		this.setIdLocal(entidad.getIdLocal());
		return this;
	}
}
