package com.unla.Grupo16OO22020.models;



public class ProductoModel {

	private int id;
	
	private String descripcion;	
	
	private int precioUnitario ;	
	
	public ProductoModel() {
		super();
	}

	public ProductoModel(int id, String descripcion, int precioUnitario) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
}
