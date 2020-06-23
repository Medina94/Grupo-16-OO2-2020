package com.unla.Grupo16OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo16OO22020.entities.Notificacion;

@Repository("notificacionRepository")
public interface INotificacionRepository extends JpaRepository<Notificacion, Serializable>{
	
	@Query("Select n from Notificacion n join n.empleado e where e.id = (:idEmpleado) AND n.confirmado=1")
	public abstract List<Notificacion> traerNotificacionesPropias(int idEmpleado);
	
	@Query("Select n from Notificacion n join n.empleado e where e.id = (:idEmpleado) AND n.confirmado=0")
	public abstract List<Notificacion> traerNotificacionesPropiasRechazadas(int idEmpleado);
	
	@Query("Select n from Notificacion n join n.empleado e where e.id = (:idEmpleado)")
	public abstract List<Notificacion> traerTodas(int idEmpleado);
}
