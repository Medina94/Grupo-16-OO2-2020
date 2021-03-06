package com.unla.Grupo16OO22020.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class Empleado extends Persona{
	private int sueldo;
	private boolean esGerente;
	private boolean eliminado;

	@OneToOne(cascade = CascadeType.MERGE)
	private Local local;

	public Empleado() {
		super();
	}

	public Empleado(int id, int dni,String nombre, String mail, String apellido, LocalDate fecha, int sueldo, boolean esGerente, Local local, boolean eliminado) {
		super();
		setId(id);
		setNombre(nombre);
		setMail(mail);
		setApellido(apellido);
		setFechaNacimiento(fecha);
		setDni(dni);
		this.sueldo = sueldo;
		this.esGerente = esGerente;
		this.local = local;
		this.eliminado = eliminado;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
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

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	
}
