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
	
	@Query(value="select l.* from local l inner join producto p on l.id = p.local_id where p.codigo = (:codigo)", nativeQuery = true)
	public abstract List<Local> getByStock(String codigo);
	
}
