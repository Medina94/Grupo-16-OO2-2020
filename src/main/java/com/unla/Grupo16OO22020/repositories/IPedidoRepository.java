package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unla.Grupo16OO22020.entities.Pedido;
import com.unla.Grupo16OO22020.models.*;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable> {
	
	public abstract Pedido findById(int id);		
	
	@Query("SELECT "
			+ "new com.unla.Grupo16OO22020.models.RankingModel(Sum(p.cantidadSolicitada), pro.codigo, pro.descripcion, pro.imagenUrl, pro.precioUnitario) "
			+ "FROM Pedido p JOIN p.producto pro JOIN pro.local l "
			+ "where p.fecha >= (:fechaDesde) and p.fecha <= (:fechaHasta) "
			+ "AND ((:localId) = 0 OR l.id = (:localId)) GROUP BY pro.codigo")
	public abstract ArrayList<RankingModel> obtenerRanking(LocalDate fechaDesde, LocalDate fechaHasta, int localId);
	
	
	@Query("SELECT p FROM Pedido p where p=(:solicitador_id)")
	public abstract List<PedidoModel> findBySolicitador_id(int solicitador_id);
	@Query("SELECT p FROM Pedido p JOIN p.producto pro JOIN pro.local l"
			+ " WHERE ((:rol) = 'ROL_ADMIN' OR l.id = (:localId))")	
	public abstract List<Pedido> obtenerPedidosPropios(int localId, String rol);
	
	@Query("SELECT new com.unla.Grupo16OO22020.models.PlusSueldoModel(e.id, e.nombre,e.sueldo,"
			+ "Sum(p.cantidadSolicitada * pro.precioUnitario))"
			+ "FROM Pedido p JOIN p.producto pro JOIN pro.local l JOIN p.solicitador e "
			+ "where l.id=(:localId) AND p.fecha>=(:fechaDesde) AND p.fecha<=(:fechaHasta) AND p.estado=1 AND p.id not in (SELECT s.pedido FROM SolicitudStock s)"
			+ "GROUP BY e.id")
	public abstract List<PlusSueldoModel> calcularPlusPedido(int localId, LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("SELECT new com.unla.Grupo16OO22020.models.DetallePedidoEmpleadoModel(pro.descripcion,p.fecha,p.cantidadSolicitada, "
			+ " pro.precioUnitario, s.nombre,(p.cantidadSolicitada * pro.precioUnitario), c.nombre)"
			+ " from Pedido p JOIN p.solicitador s JOIN p.producto pro "
			+ " JOIN p.cliente c WHERE s.id=(:empleado_id) AND p.estado= 1 AND p.fecha>=(:fechaDesde) AND p.fecha<=(:fechaHasta)"
			+ " AND p.id not in (SELECT s.pedido FROM SolicitudStock s)")
	public abstract List<DetallePedidoEmpleadoModel> obtenerPedidosPorEmpleado(int empleado_id, LocalDate fechaDesde, LocalDate fechaHasta);
	
	
}
