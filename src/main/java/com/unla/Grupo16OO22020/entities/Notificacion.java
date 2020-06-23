package com.unla.Grupo16OO22020.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="notificacion")
public class Notificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade = CascadeType.MERGE)
	private Empleado empleado;
	
	private boolean confirmado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}	
	public boolean isConfirmado() {
		return confirmado;
	}
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}
	public Notificacion(int id, Empleado empleado, boolean confirmado) {
		this.id = id;
		this.empleado = empleado;
		this.confirmado = confirmado;
	}
	public Notificacion() {}
	
	
}
