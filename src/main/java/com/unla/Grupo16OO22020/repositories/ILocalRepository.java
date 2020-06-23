package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.Local;

@Repository("localRepository")
public interface ILocalRepository extends JpaRepository<Local, Serializable>{

	public abstract Local findById(int id);	
	
	@Query(value="select l.* from local l inner join producto p on l.id = p.local_id where p.codigo = (:codigo) group by l.id", nativeQuery = true)
	public abstract List<Local> getByStock(String codigo);
	
	@Query("SELECT l FROM Local l WHERE ((:rol) = 'ROLE_ADMIN' OR l.id = (:localId))")
	public abstract List<Local> traerTodo(int localId, String rol);
	
}
