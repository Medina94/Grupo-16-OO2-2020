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
	
	@Query(value="select p.* from producto p inner join local l on p.local_id = l.id where p.codigo = (:codigo) and l.id = (:local) group by l.id", nativeQuery = true)
	public abstract Producto findByCodigoAndLocal(String codigo, int local);
	
	@Query("SELECT p FROM Producto p WHERE p.eliminado=false")
	public abstract List<Producto> getAll();
	
	@Query("SELECT p FROM Producto p JOIN FETCH p.local l where l.id = (:localId) and p.eliminado=false Group by p.codigo")
	public abstract List<Producto> traerTodoProductoDeLocal(int localId);
}
