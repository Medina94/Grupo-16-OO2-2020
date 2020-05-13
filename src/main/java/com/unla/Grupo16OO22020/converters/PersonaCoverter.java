package com.unla.Grupo16OO22020.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.Cliente;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;

@Component("personaConverter")
public class PersonaCoverter {
	
	public Cliente ClienteModelToEntity(ClienteModel modelo) {
		return new Cliente().build(modelo);
	}
	
	public ClienteModel ClienteEntitytoModel(Cliente entidad) {
		return new ClienteModel().build(entidad);
	}
	
	public Empleado EmpleadoModelToEntity(EmpleadoModel modelo) {
		return new Empleado().build(modelo);
	}
	
	public EmpleadoModel EmpleadoEntitytoModel(Empleado entidad) {
		return new EmpleadoModel().build(entidad);
	}
}
