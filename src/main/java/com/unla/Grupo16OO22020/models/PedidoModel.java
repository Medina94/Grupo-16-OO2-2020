package com.unla.Grupo16OO22020.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PedidoModel {


	private int id;
	
	private int cantidadSolicitada;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	
	private ProductoModel productoModel; 
	
	private ClienteModel clienteModel; 
	
	private EmpleadoModel solicitadorModel;
	
	private double Total;
	
	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public PedidoModel() {
		super();
	}

	public PedidoModel(int id, int cantidadSolicitada, LocalDate fecha, ProductoModel productoModel,
			ClienteModel clienteModel, EmpleadoModel solicitadorModel) {
		super();
		this.id = id;
		this.cantidadSolicitada = cantidadSolicitada;
		this.fecha = fecha;
		this.productoModel = productoModel;
		this.clienteModel = clienteModel;
		this.solicitadorModel = solicitadorModel;
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

	public ProductoModel getProductoModel() {
		return productoModel;
	}

	public void setProductoModel(ProductoModel productoModel) {
		this.productoModel = productoModel;
	}

	public ClienteModel getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}

	public EmpleadoModel getSolicitadorModel() {
		return solicitadorModel;
	}

	public void setSolicitadorModel(EmpleadoModel solicitadorModel) {
		this.solicitadorModel = solicitadorModel;
	}

}
