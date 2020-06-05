package com.unla.Grupo16OO22020.models;



public class LocalModel implements Comparable<LocalModel>{

private int id;
	
	private int telefono;
	private String direccion;
	private double latitud;
	private double longitud;
	private double distanciaDelOrigen;
	
	
	public LocalModel() {}

	public LocalModel(int id, int telefono, String direccion, double latitud, double longitud) {
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

	public double getDistanciaDelOrigen() {
		return distanciaDelOrigen;
	}

	public void setDistanciaDelOrigen(double distanciaDelOrigen) {
		String valor = distanciaDelOrigen+"";
		valor = valor.substring(0, valor.indexOf('.')+3); // recorto el string hasta el indice donde se encuentra el punto decimal más 3 (para tomar 2 decimales)
		this.distanciaDelOrigen = Double.parseDouble(valor);
	}

	@Override
	public int compareTo(LocalModel o) {
		int result = 0;
		if(this.distanciaDelOrigen > o.getDistanciaDelOrigen()) {
			result = 1;
		}else if(this.distanciaDelOrigen < o.getDistanciaDelOrigen()) {
			result = -1;
		}
		return result;
	}
	
//	public Empleado getEmpleado() {
//		return Empleado;
//	}
//
//	public void setLongitud(Empleado empleado) {
//		this.empleado = empleado;
//	}
	
	
}
