package com.dawii.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dawii.entity.Habitacion;

import jakarta.transaction.Transactional;

@Repository
public interface IHabitacionDAO extends JpaRepository<Habitacion, Long>{
	
	//Buscar ultimo piso
	@Query("SELECT COALESCE(MAX(piso), 0) FROM Habitacion")
	public int buscarUltimoPiso();
	
	// Buscar por piso
	@Query("SELECT H FROM Habitacion H WHERE H.piso=?1 AND H.hotel.sede.id=?2 ")
	public List<Habitacion> buscarXPisoAndSede(String piso,long idSede);
	
	//Buscar por tipo
	@Query("SELECT H FROM Habitacion H "
		 + "INNER JOIN H.tipo T WHERE T.id=?1")
	public List<Habitacion> buscarXTipo(int tipo);
	
	//Actualizar estado
	@Transactional
    @Modifying
	@Query("UPDATE Habitacion H SET H.estado=?1 WHERE H.id=?2")
	public int actualizarEstado(String estado, long id);

	public Habitacion findFirstByPisoOrderByNumeroDesc(String piso);
	
	@Query("SELECT H FROM Habitacion H WHERE (H.hotel.sede.id=?1) AND (H.tipo.nombre = ?2 OR '' = ?2) AND (H.estado = 'disponible')")
	public List<Habitacion> buscarXSedeAndTipo(long sede,String tipo);
	
}
