package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unla.Grupo16OO22020.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable> {
	
	public abstract Lote findById(int id);
	@Query("SELECT l FROM Lote l JOIN FETCH l.producto p join fetch p.local lo WHERE p.codigo = (:codigo) and lo.id = (:idLocal)")
	public abstract List<Lote> findByProducto(String codigo, int idLocal);
	
}
