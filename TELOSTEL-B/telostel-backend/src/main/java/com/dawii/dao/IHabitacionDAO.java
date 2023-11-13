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
	
	//Buscar por piso
	public List<Habitacion> findByPiso(int piso);
	
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

}
