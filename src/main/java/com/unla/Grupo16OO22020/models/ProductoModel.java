package com.unla.Grupo16OO22020.models;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.unla.Grupo16OO22020.entities.Local;

public class ProductoModel {

	private int id;
	
	private String descripcion;	
	
	private int precioUnitario ;	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")//yyyy-MM-dd	
	private LocalDate fechaAlta;
	
	private LocalModel localModel;
	
	private boolean eliminado;
	public ProductoModel() {
		super();
	}	
	
	public ProductoModel(int id, String descripcion, int precioUnitario, LocalModel localModel, 
			LocalDate fechaAlta,boolean eliminado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.localModel = localModel;
		this.fechaAlta = fechaAlta;
		this.eliminado = eliminado;
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
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}


	
	
}
