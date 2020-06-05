package com.unla.Grupo16OO22020.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double plusVenta;
	private double plusCeder;
	private double plusPedido;
	private LocalDate fecha;
	
	public Comision() {}
	
	public Comision(int id ,double plusVenta, double plusCeder, double plusPedido,LocalDate fecha) {
		this.id = id;
		this.plusVenta = plusVenta;
		this.plusCeder = plusCeder;
		this.plusPedido = plusPedido;
		this.fecha = fecha;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPlusVenta() {
		return plusVenta;
	}

	public void setPlusVenta(double plusVenta) {
		this.plusVenta = plusVenta;
	}

	public double getPlusCeder() {
		return plusCeder;
	}

	public void setPlusCeder(double plusCeder) {
		this.plusCeder = plusCeder;
	}

	public double getPlusPedido() {
		return plusPedido;
	}

	public void setPlusPedido(double plusPedido) {
		this.plusPedido = plusPedido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
