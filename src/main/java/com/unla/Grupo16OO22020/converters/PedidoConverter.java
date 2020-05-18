package com.unla.Grupo16OO22020.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.Grupo16OO22020.entities.Lote;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.models.LoteModel;
import com.unla.Grupo16OO22020.models.PedidoModel;

@Component("pedidoConverter")
public class PedidoConverter {

	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	public PedidoModel entityToModel(Pedido pedido) {
		return new PedidoModel(pedido.getId(), pedido.getCantidadSolicitada(),
				pedido.getFecha(), productoConverter.entityToModel(pedido.getProducto()), 
				personaConverter.ClienteEntitytoModel(pedido.getCliente()), 
				personaConverter.EmpleadoEntitytoModel(pedido.getSolicitador()));
	}

	public Pedido modelToEntity(PedidoModel pedidoModel) {
		return new Pedido(pedidoModel.getId(), pedidoModel.getCantidadSolicitada(),
			pedidoModel.getFecha(),	productoConverter.modelToEntity(pedidoModel.getProductoModel()),
			personaConverter.ClienteModelToEntity(pedidoModel.getClienteModel()), 
			personaConverter.EmpleadoModelToEntity(pedidoModel.getSolicitadorModel()));
	}
}

