package com.unla.Grupo16OO22020.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="solicitudStock")
public class SolicitudStock {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Empleado colaborador;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Empleado solicitador;
	
	@Column(name="estado")
	private int estado;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Pedido pedido;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Local local;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Empleado getColaborador() {
		return colaborador;
	}
	public void setColaborador(Empleado colaborador) {
		this.colaborador = colaborador;
	}
	public Empleado getSolicitador() {
		return solicitador;
	}
	public void setSolicitador(Empleado solicitador) {
		this.solicitador = solicitador;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	
	public SolicitudStock() {}
	
	public SolicitudStock(Empleado solicitador, int estado, Pedido pedido, Local local) {
		this.solicitador = solicitador;
		this.estado = estado;
		this.pedido = pedido;
		this.local = local;
	}

}
