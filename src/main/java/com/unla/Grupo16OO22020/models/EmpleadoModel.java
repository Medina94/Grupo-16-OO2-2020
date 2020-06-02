package com.unla.Grupo16OO22020.models;

import java.time.LocalDate;

import com.unla.Grupo16OO22020.entities.Empleado;

public class EmpleadoModel extends PersonaModel{
	private int sueldo;
	private boolean esGerente;
	private LocalModel localModel;
	
	public EmpleadoModel() {}
	
	
	public EmpleadoModel(int id, int dni,String nombre, String mail, String apellido, LocalDate fecha, int sueldo, boolean esGerente, LocalModel localModel) {
		super();
		setId(id);
		setNombre(nombre);
		setMail(mail);
		setApellido(apellido);
		setFechaNacimiento(fecha);
		setDni(dni);
		this.sueldo = sueldo;
		this.esGerente = esGerente;
		this.localModel = localModel;
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
	

	public LocalModel getLocalModel() {
		return localModel;
	}

	public void setLocalModel(LocalModel localModel) {
		this.localModel = localModel;
	}

}
