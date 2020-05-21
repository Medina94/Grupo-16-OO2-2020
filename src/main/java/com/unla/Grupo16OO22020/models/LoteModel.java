package com.unla.Grupo16OO22020.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LoteModel {


	private int id;
	
	private int cantidad;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;
	
	private ProductoModel productoModel; 
	
	public LoteModel() {
		super();
	}


	public LoteModel(int id, int cantidad, LocalDate fechaIngreso, ProductoModel productoModel) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.productoModel = productoModel;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public ProductoModel getProductoModel() {
		return productoModel;
	}

	public void setProductoModel(ProductoModel productoModel) {
		this.productoModel = productoModel;
	}

}
