package com.unla.Grupo16OO22020.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ProductoModel {

	private int id;
	private String descripcion;	
	private int precioUnitario ;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")//yyyy-MM-dd
	private LocalDate fechaAlta;
	
	public ProductoModel() {
		super();
	}

	public ProductoModel(int id, String descripcion, int precioUnitario,LocalDate fechaAlta) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.fechaAlta = fechaAlta;
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
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
