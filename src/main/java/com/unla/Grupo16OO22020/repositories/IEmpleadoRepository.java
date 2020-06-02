package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.Cliente;
import com.unla.Grupo16OO22020.entities.Empleado;

@Repository("empleadoRepository")
public interface IEmpleadoRepository extends JpaRepository<Empleado, Serializable>{
	public abstract Empleado findById(int id);

	public abstract Empleado findByDni(int dni);
	
	@Query("SELECT e FROM Empleado e WHERE e.eliminado=false")
	public abstract List<Empleado> getAllEmpleado();
}
