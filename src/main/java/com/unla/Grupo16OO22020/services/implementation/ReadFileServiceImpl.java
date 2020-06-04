package com.unla.Grupo16OO22020.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.Grupo16OO22020.helpers.LeerTxt;
import com.unla.Grupo16OO22020.models.ClienteModel;
import com.unla.Grupo16OO22020.services.ReadFileService;

@Service
public class ReadFileServiceImpl implements ReadFileService{
	@Autowired
	private PersonaService personaService;
	private static final String PATH_EMPLEADO = "src\\main\\resources\\text\\empleado";
	
	@SuppressWarnings("unchecked")
	@Override
	public void insertarEmpleado() {
		List<ClienteModel> lista = new ArrayList<>();
		lista = (List<ClienteModel>)(Object)LeerTxt.leer(PATH_EMPLEADO);
		for(ClienteModel em : lista) {
			personaService.clienteInsertOrUpdate(em);
		}
	}
	
}

