package com.unla.Grupo16OO22020.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="local")
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="telefono")
	private int telefono;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="latitud")
	private double latitud;
	
	@Column(name="longitud")
	private double longitud;
	
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="empleado")
//	private Set<Empleado> empleados = new HashSet<Empleado>();
	
	public Local() {}

	public Local(int id, int telefono, String direccion, double latitud, double longitud) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


	
	
}
