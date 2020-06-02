package com.unla.Grupo16OO22020.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.Cliente;
import com.unla.Grupo16OO22020.entities.Empleado;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.models.EmpleadoModel;
import com.unla.Grupo16OO22020.services.implementation.LocalService;

@Component("personaConverter")
public class PersonaConverter {
	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	public Cliente ClienteModelToEntity(ClienteModel modelo) {
		return new Cliente().build(modelo);
	}
	
	public ClienteModel ClienteEntitytoModel(Cliente entidad) {
		return new ClienteModel().build(entidad);
	}
	
	public Empleado EmpleadoModelToEntity(EmpleadoModel modelo) {
		return new Empleado(modelo.getId(), modelo.getDni(), modelo.getNombre(),  modelo.getMail(), modelo.getApellido(), modelo.getFechaNacimiento(), modelo.getSueldo(), modelo.isEsGerente(), localConverter.modelToEntity(modelo.getLocalModel()),modelo.isEliminado());
	}
	
	public EmpleadoModel EmpleadoEntitytoModel(Empleado entidad) {
		return new EmpleadoModel(entidad.getId(), entidad.getDni(), entidad.getNombre(), entidad.getMail(), entidad.getApellido(), entidad.getFechaNacimiento(), entidad.getSueldo(), entidad.isEsGerente(), localConverter.entityToModel(entidad.getLocal()),entidad.isEliminado());
	}
}
