package com.unla.Grupo16OO22020.services;

import java.util.List;

import com.unla.Grupo16OO22020.entities.Cliente;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;

public interface IPersonaService{

	public List<Cliente> getAllCliente();
	public List<Empleado> getAllEmpleado();
	
	public ClienteModel clienteFindById(int id);
	public EmpleadoModel empleadoFindById(int id);
	
	public ClienteModel clienteInsertOrUpdate(ClienteModel model);
	public EmpleadoModel empleadoInsertOrUpdate(EmpleadoModel model);
	
	public boolean clienteRemove(int id);
	public boolean EmpleadoRemove(int id);
	
	public void crearUsuario (EmpleadoModel modelo);
	
	public String validarEmpleado(int dni);
	String validarCliente(int dni);
	
	/**
	 * Trae todos los empleados != al que se encuentra logueado
	 */
	public List<EmpleadoModel> obtenerEmpleados(int id);
	
}
