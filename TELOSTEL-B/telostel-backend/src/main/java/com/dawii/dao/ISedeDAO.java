package com.dawii.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawii.entity.Sede;

public interface ISedeDAO extends JpaRepository<Sede, Long>{

	@Query("SELECT H.sede FROM Hotel H")
	public List<Sede> listarSedes();
	
}
