package com.unla.Grupo16OO22020.services;
import java.util.List;

import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.models.PedidoModel;


public interface IPedidoService {

	public List<Pedido> getAll();
	
	public PedidoModel findById(int id);
	
	public PedidoModel insertOrUpdate(PedidoModel pedidoModel);
	
	public boolean remove(int id);
	
	public boolean consultarStock(int idProducto, int cantidadSolicitada);
	
	public void actualizarStock(PedidoModel pedidoModel);
	
	public void rechazarPedido(int pedidoId);
	
	public PedidoModel calcularTotal(int pedidoId);
	
}

