package com.unla.Grupo16OO22020.models;

import java.time.LocalDate;

public class DetallePedidoEmpleadoModel {

	
	private String nombreProducto;
	private LocalDate fecha;
	private int cantidadSolicitada;
	private int precioUnitario;
	private String nombreEmpleado;
	private int subTotal;
	private double total;
	private String nombreCliente;
	
	public DetallePedidoEmpleadoModel(String nombreProducto, LocalDate fecha, int cantidadSolicitada,
			int precioUnitario, String nombreEmpleado, int subTotal, String nombreCliente) {
		
		this.nombreProducto = nombreProducto;
		this.fecha = fecha;
		this.cantidadSolicitada = cantidadSolicitada;
		this.precioUnitario = precioUnitario;
		this.nombreEmpleado = nombreEmpleado;
		this.subTotal = subTotal;
		this.nombreCliente = nombreCliente;
	}
	
	

	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}
	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
	public int getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public int getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
	
}
