package com.dawii.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawii.entity.Reservacion;

public interface IReservacionDAO extends JpaRepository<Reservacion,Long>{

	@Query("SELECT CASE WHEN COUNT(R) > 0 THEN true ELSE false END FROM Reservacion R WHERE R.cliente.id = ?1")
	public boolean clienteConReserva(long id);
	
	@Query("SELECT r FROM Reservacion r WHERE r.checkIn BETWEEN ?1 AND ?2 AND r.checkOut BETWEEN ?1 AND ?2")
	public List<Reservacion> FiltrarReservacionFechas(LocalDate fecInicial, LocalDate fecFinal);

	
}
