package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {
	
	public abstract Producto findById(int id);	
	@Query("SELECT p FROM Producto p WHERE p.eliminado=false")
	public abstract List<Producto> getAll();
	@Query("Select Producto from Producto p Join Fetch Local l where p.codigo = (:codigo) and l.id = (:local)")
	public abstract Producto findByCodigoAndLocal(String codigo, int local);
}
