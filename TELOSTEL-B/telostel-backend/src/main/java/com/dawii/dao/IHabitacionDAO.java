package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawii.entity.Habitacion;

@Repository
public interface IHabitacionDAO extends JpaRepository<Habitacion, Long>{
	
}
