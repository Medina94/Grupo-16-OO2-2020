package com.unla.Grupo16OO22020.services;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.unla.Grupo16OO22020.entities.Lote;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.models.DetallePedidoEmpleadoModel;
import com.unla.Grupo16OO22020.models.FacturaModel;
import com.unla.Grupo16OO22020.models.LocalModel;
import com.unla.Grupo16OO22020.models.PedidoModel;
import com.unla.Grupo16OO22020.models.PlusSueldoModel;
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
	
	public List<PlusSueldoModel> calcularPlusPedido();
	
	public  List<DetallePedidoEmpleadoModel> obtenerPedidosPorEmpleado(int empleado_id);
	
	public int calcularPlusTotal(int empleado);
	
	public LocalDate obtenerUltimoDiaDelAño();
	
	public LocalDate obtenerPrimerDiaDelAño();
	
	public List<Lote> lotes (int idProducto);
	
	public int devolverCantidadStockDisponible(List<Lote> lotes);
	
	public List<FacturaModel> generarFactura(int pedidoId);
	
	public int cantidadStock(int idProducto, int cantidadSolicitada);
}

