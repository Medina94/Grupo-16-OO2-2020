package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.time.LocalDate;
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
			+ "new com.unla.Grupo16OO22020.models.Ranking(Count(p.cantidadsolicitada), pro.Codigo, pro.Descripcion) "
			+ "FROM pedido p JOIN FETCH producto pro JOIN FETCH local l "
			+ "where p.fecha >= (:fechaDesde) and p.fecha <= (:fechaHasta) "
			+ "AND ((:localId) = 0 OR l.id = (:localId)) GROUP BY pro.codigo")
	public abstract List<RankingModel> obtenerRanking(LocalDate fechaDesde, LocalDate fechaHasta, int localId);
	
	
	
	
	
}
