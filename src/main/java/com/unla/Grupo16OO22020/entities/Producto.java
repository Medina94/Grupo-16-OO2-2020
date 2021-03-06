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
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="precioUnitario")
	private int precioUnitario ;
	
	@Column(name="FechaAlta")
	private LocalDate fechaAlta ;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Local local;
	
	@Column(name="eliminado")
	private boolean eliminado;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="imagenUrl")
	private String imagenUrl;
	
	public Producto() {
		super();
	}

	public Producto(int id, String descripcion, int precioUnitario, LocalDate fechaAlta, Local local, boolean eliminado, String codigo,String imagenUrl) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.fechaAlta = fechaAlta;
		this.local = local;
		this.eliminado= eliminado; //duda si va aca o hay que poner el atributo false
		this.codigo = codigo;
		this.imagenUrl = imagenUrl;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Local getLocal() {
		return local;
	}


	public void setLocal(Local local) {
		this.local = local;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
}
