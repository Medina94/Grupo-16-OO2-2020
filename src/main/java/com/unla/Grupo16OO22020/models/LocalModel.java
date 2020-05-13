package com.unla.Grupo16OO22020.models;

public class LocalModel {

private int id;
	
	private int telefono;
	private String direccion;
	private int latitud;
	private int longitud;
	//private EmpleadoModel empleado;
	
	public LocalModel() {}

	public LocalModel(int id, int telefono, String direccion, int latitud, int longitud) {
		super();
		this.setId(id);
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

	public int getLatitud() {
		return latitud;
	}

	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
//	public Empleado getEmpleado() {
//		return Empleado;
//	}
//
//	public void setLongitud(Empleado empleado) {
//		this.empleado = empleado;
//	}
	
}
