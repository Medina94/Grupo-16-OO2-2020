package com.unla.Grupo16OO22020.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="lote")
public class Lote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cantidad")
	private int cantidad;
	@Column(name="FechaIngreso")
	private LocalDate fechaIngreso;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Producto producto;
	
	public Lote() {
		super();
	}

	public Lote(int id, int cantidad, LocalDate fechaIngreso, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
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


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
