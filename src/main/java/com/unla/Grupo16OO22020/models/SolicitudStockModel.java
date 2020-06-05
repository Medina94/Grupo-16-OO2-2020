package com.unla.Grupo16OO22020.models;

import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.entities.Pedido;

public class SolicitudStockModel {
	private int id;
	private Empleado colaborador;
	private Empleado solicitador;
	private int estado;
	private Pedido pedido;
	private LocalModel local;

	public SolicitudStockModel() {}

	public SolicitudStockModel(/*int id, */Empleado colaborador, Empleado solicitador, int estado, Pedido pedido,
			LocalModel local) {
		/*this.id = id;*/		
		this.colaborador = colaborador;
		this.solicitador = solicitador;
		this.estado = estado;
		this.pedido = pedido;
		this.local = local;
	}

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

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}
	
	
}
