package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.SolicitudStock;

@Repository("solicitudStockRepository")
public interface ISolicitudStockRepository extends JpaRepository<SolicitudStock, Serializable>{
	public abstract SolicitudStock findById(int id);
	
	@Query("Select s from SolicitudStock s join s.pedido p join p.producto pro join pro.local l where l.id = (:localId)")
	public abstract List<SolicitudStock> obtenerSolicitudesRealizadas(int localId);
	
	@Query("Select s from SolicitudStock s join s.local l where l.id = (:localId)")
	public abstract List<SolicitudStock> obtenerSolicitudesRecibidas(int localId);
}
