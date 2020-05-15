package com.unla.Grupo16OO22020.models;

public class LocalidadModel {
	
	private int id;	
	private String nombre;
	private int latitud;
	private int longitud;
	
	public LocalidadModel() {}
	
	public LocalidadModel(int id, String nombre, int longitud, int latitud) {
		this.setId(id);
		this.nombre = nombre;		
		this.latitud = latitud;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

