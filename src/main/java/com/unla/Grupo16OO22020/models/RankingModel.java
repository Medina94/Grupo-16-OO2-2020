package com.unla.Grupo16OO22020.models;

public class RankingModel {

	private int id;
	
	private int total;
	
	private String codigo;	
	
	private String descripcion;	
	
	
	public RankingModel() {
		super();
	}	
	

	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public RankingModel(int total, String codigo, String descripcion) {
		super();
		this.total = total;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	

	
	
}
