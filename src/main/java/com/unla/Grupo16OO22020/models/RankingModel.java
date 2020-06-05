package com.unla.Grupo16OO22020.models;

public class RankingModel {

	private int id;
	
	private long total;
	
	private String codigo;	
	
	private String descripcion;	
	
	private String url;	
	
	private int precioUnitario;
	
	public RankingModel() {
		super();
	}	

	public RankingModel(long total, String codigo, String descripcion, String url, int precioUnitario) {
		super();
		this.total = total;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.url = url;
		this.precioUnitario = precioUnitario;
	}

	public int getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public long getTotal() {
		return total;
	}


	public void setTotal(long total) {
		this.total = total;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
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
