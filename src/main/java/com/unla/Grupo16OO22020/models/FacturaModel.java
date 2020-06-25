package com.unla.Grupo16OO22020.models;

import java.time.LocalDate;

public class FacturaModel {

	public String nombre;
	public String apellido;
	public LocalDate fechanacimiento;
	public int telefono;
	public String mail;
	public int dni;
	public int cantidadsolicitada;
	public LocalDate fecha;
	public String descripcion;
	public String imagenurl;
	public int preciounitario;
	public String direccion;
	public int telefonolocal;


	public FacturaModel(String nombre, String apellido, LocalDate fechanacimiento, int telefono, String mail,
			int dni, int cantidadsolicitada, LocalDate fecha, String descripcion, String imagenurl,
			int preciounitario, String direccion, int telefonolocal) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechanacimiento = fechanacimiento;
		this.telefono = telefono;
		this.mail = mail;
		this.dni = dni;
		this.cantidadsolicitada = cantidadsolicitada;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.imagenurl = imagenurl;
		this.preciounitario = preciounitario;
		this.direccion = direccion;
		this.telefonolocal = telefonolocal;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(LocalDate fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getCantidadsolicitada() {
		return cantidadsolicitada;
	}

	public void setCantidadsolicitada(int cantidadsolicitada) {
		this.cantidadsolicitada = cantidadsolicitada;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getImagenurl() {
		return imagenurl;
	}

	public void setImagenurl(String imagenurl) {
		this.imagenurl = imagenurl;
	}

	public int getPreciounitario() {
		return preciounitario;
	}

	public void setPreciounitario(int preciounitario) {
		this.preciounitario = preciounitario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefonolocal() {
		return telefonolocal;
	}

	public void setTelefonolocal(int telefonolocal) {
		this.telefonolocal = telefonolocal;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
