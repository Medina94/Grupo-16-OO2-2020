package com.unla.Grupo16OO22020.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.unla.Grupo16OO22020.entities.Local;

public class ProductoModel {

	private int id;
	
	private String descripcion;	
	
	private int precioUnitario;	
	
	private LocalModel localModel;
	
	@CreationTimestamp
	private LocalDateTime fechaAlta;


	public ProductoModel() {
		super();
	}


	public ProductoModel(int id, String descripcion, int precioUnitario, LocalModel localModel,
			LocalDateTime fechaAlta) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.localModel = localModel;
		this.fechaAlta = fechaAlta;
	}


	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}




	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}




	public LocalModel getLocalModel() {
		return localModel;
	}


	public void setLocalModel(LocalModel localModel) {
		this.localModel = localModel;
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
