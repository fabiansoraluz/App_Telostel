package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dawii.entity.TipoHabitacion;

public interface ITipoHabitacionDAO extends JpaRepository<TipoHabitacion, Long>{
	
    @Query("SELECT t FROM TipoHabitacion t WHERE t.id = :idTipoHabitacion")
    TipoHabitacion findByIdTipoHabitacion(@Param("idTipoHabitacion") Long idTipoHabitacion);
}
