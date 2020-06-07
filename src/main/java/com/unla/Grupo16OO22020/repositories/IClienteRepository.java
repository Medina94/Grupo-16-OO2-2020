package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.Cliente;
import com.unla.Grupo16OO22020.entities.Empleado;

@Repository("clienteRepository")
public interface IClienteRepository extends JpaRepository<Cliente, Serializable> {
	public abstract Cliente findById(int id);
	
	public abstract Cliente findByDni(int dni);
	//public abstract Cliente findByName(String name);

	// Todas las personas que tengan un título con ese nombre (parámetro name)
	// @Query("SELECT p FROM Person p JOIN FETCH p.degrees d WHERE d.name =
	// (:name)")
	// public abstract List<Cliente> findByDegreeName(String name);
	
	@Query("SELECT c FROM Cliente c WHERE c.eliminado=false")
	public abstract List<Cliente> getAllCliente();
}
