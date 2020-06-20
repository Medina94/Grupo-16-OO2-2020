package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.SolicitudStock;
import com.unla.Grupo16OO22020.models.DetallePedidoEmpleadoModel;
import com.unla.Grupo16OO22020.models.PlusSueldoModel;

@Repository("solicitudStockRepository")
public interface ISolicitudStockRepository extends JpaRepository<SolicitudStock, Serializable>{
	public abstract SolicitudStock findById(int id);
	
	@Query("Select s from SolicitudStock s join s.pedido p join p.producto pro join pro.local l where (:localId) = 0 OR l.id = (:localId)")
	public abstract List<SolicitudStock> obtenerSolicitudesRealizadas(int localId);
	
	@Query("Select s from SolicitudStock s join s.local l where (:localId) = 0 OR l.id = (:localId)")
	public abstract List<SolicitudStock> obtenerSolicitudesRecibidas(int localId);
	
	
//	public abstract List<SolicitudStock> calcularPlusCeder (int localId, LocalDate fechaDesde, LocalDate fechaHasta); 
	

	
	@Query("SELECT new com.unla.Grupo16OO22020.models.DetallePedidoEmpleadoModel(pro.descripcion,p.fecha,p.cantidadSolicitada, "
			+ "pro.precioUnitario, e.nombre,(p.cantidadSolicitada * pro.precioUnitario),c.nombre)"
			+ "from SolicitudStock s JOIN s.pedido p JOIN p.producto pro "
			+ "JOIN p.cliente c JOIN s.solicitador e WHERE e.id=(:empleado_id) AND p.fecha>=(:fechaDesde) AND p.fecha<=(:fechaHasta) AND s.estado= 1")
	public abstract List<DetallePedidoEmpleadoModel> obtenerSolicitudesConfirmadasPorEmpleado(int empleado_id, LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("Select new com.unla.Grupo16OO22020.models.PlusSueldoModel(e.id,e.nombre,e.sueldo,"
			+ "Sum(p.cantidadSolicitada * pro.precioUnitario)) from SolicitudStock s"
			+ " JOIN s.pedido p JOIN p.producto pro "
			+ "JOIN s.solicitador e JOIN e.local l where l.id=(:localId) AND p.fecha>=(:fechaDesde) AND p.fecha<=(:fechaHasta) AND s.estado=1"
			+ " GROUP BY e.id")
	public abstract List<PlusSueldoModel> calcularPlusSolicitar(int localId, LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("SELECT new com.unla.Grupo16OO22020.models.DetallePedidoEmpleadoModel(pro.descripcion,p.fecha,p.cantidadSolicitada, "
			+ "pro.precioUnitario, e.nombre,(p.cantidadSolicitada * pro.precioUnitario),c.nombre)"
			+ "from SolicitudStock s JOIN s.colaborador e JOIN s.pedido  p JOIN p.producto pro "
			+ "JOIN p.cliente c WHERE e.id=(:empleado_id) AND p.fecha>=(:fechaDesde) AND p.fecha<=(:fechaHasta) AND s.estado= 1")
	public abstract List<DetallePedidoEmpleadoModel> obtenerSolicitudesCedidasAOtroEmpleado(int empleado_id, LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("Select new com.unla.Grupo16OO22020.models.PlusSueldoModel(e.id,e.nombre,e.sueldo,"
			+ "Sum(p.cantidadSolicitada * pro.precioUnitario)) from SolicitudStock s"
			+ " JOIN s.pedido p JOIN p.producto pro "
			+ "JOIN s.colaborador e JOIN e.local l where l.id=(:localId) AND p.fecha>=(:fechaDesde) AND p.fecha<=(:fechaHasta) AND s.estado=1"
			+ " GROUP BY e.id")
	public abstract List<PlusSueldoModel> calcularPlusCeder(int localId, LocalDate fechaDesde, LocalDate fechaHasta);
}
