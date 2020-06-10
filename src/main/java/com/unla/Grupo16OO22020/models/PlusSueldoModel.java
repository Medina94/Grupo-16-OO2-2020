package com.unla.Grupo16OO22020.models;

public class PlusSueldoModel {

	private int empleadoId;
	private String nombre;
	private int sueldo;
	private double plus;
	private long totalPedidos;
//	public PlusSueldoModel(String nombre, int sueldo, long plus, long totalPedidos) {
//		super();
//		this.nombre = nombre;
//		this.sueldo = sueldo;
//		this.plus = plus;
//		this.totalPedidos = totalPedidos;
//	}
	
	
	
	public PlusSueldoModel() {}

	public PlusSueldoModel(int empleadoId,String nombre, int sueldo, long totalPedidos) {
	super();
	this.empleadoId = empleadoId;
	this.nombre = nombre;
	this.sueldo = sueldo;
	this.totalPedidos = totalPedidos;
}

	
	public int getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public double getPlus() {
		return plus;
	}

	public void setPlus(double plus) {
		this.plus = plus;
	}

	public long getTotalPedidos() {
		return totalPedidos;
	}

	public void setTotalPedidos(long totalPedidos) {
		this.totalPedidos = totalPedidos;
	}









	
	
	
}