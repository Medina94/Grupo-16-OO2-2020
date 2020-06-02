package com.unla.Grupo16OO22020.enums;

public enum EstadoEnum {
	ESTADO_ACEPTADO(1, "Aceptado"),
	ESTADO_PENDIENTE(2, "Pendiente"),
	ESTADO_RECHAZADO(3, "Rechazado");
	
	private int codigo;
	private String descripcion;
	
	private EstadoEnum() {}

	private EstadoEnum(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
