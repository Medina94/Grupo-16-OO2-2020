package com.unla.Grupo16OO22020.services;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.RankingModel;


public interface IPedidoService {

	public List<Pedido> getAll();
	
	public PedidoModel findById(int id);
	
	public PedidoModel insertOrUpdate(PedidoModel pedidoModel);
	
	public boolean remove(int id);
	
	public boolean consultarStock(int idProducto, int cantidadSolicitada);
	
	public void actualizarStock(PedidoModel pedidoModel);
	
	public void rechazarPedido(int pedidoId);
	
	public PedidoModel calcularTotal(int pedidoId);
	
	public List<LocalModel> obtenerLocalesCercanosConStockDisponible(String codigo, int cantidadSolicitada);
	
	public ArrayList<RankingModel> obtenerRanking(LocalDate fechaDesde, LocalDate fechaHasta, int localId);

	public List<Pedido> obtenerPedidosPropios(int localId, int estado);
}

