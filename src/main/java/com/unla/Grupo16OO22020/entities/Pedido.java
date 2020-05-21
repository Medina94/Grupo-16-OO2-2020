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
@Table(name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cantidadSolicitada")
	private int cantidadSolicitada;
	
	@Column(name="fecha")
	private LocalDate fecha;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Producto producto;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Cliente cliente;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Empleado solicitador;
	
	
	public Pedido(int id, int cantidadSolicitada, LocalDate fecha, Producto producto, Cliente cliente,
			Empleado solicitador) {
		super();
		this.id = id;
		this.cantidadSolicitada = cantidadSolicitada;
		this.fecha = fecha;
		this.producto = producto;
		this.cliente = cliente;
		this.solicitador = solicitador;
	}


	public Empleado getSolicitador() {
		return solicitador;
	}


	public void setSolicitador(Empleado solicitador) {
		this.solicitador = solicitador;
	}


	public Pedido() {
		super();
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

}
