package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawii.entity.Reservacion;

public interface IReservacionDAO extends JpaRepository<Reservacion,Long>{

	@Query("SELECT CASE WHEN COUNT(R) > 0 THEN true ELSE false END FROM Reservacion R WHERE R.cliente.id = ?1")
	public boolean clienteConReserva(long id);
	
}
