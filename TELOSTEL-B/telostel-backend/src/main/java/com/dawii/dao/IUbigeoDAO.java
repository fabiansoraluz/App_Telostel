package com.dawii.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dawii.entity.Ubigeo;

@Repository
public interface IUbigeoDAO extends JpaRepository<Ubigeo, Long>{
	
	@Query("SELECT DISTINCT U.provincia FROM Ubigeo U")
	public List<String> listarProvincias();
	
	@Query("SELECT U FROM Ubigeo U WHERE U.provincia=?1")
	public List<Ubigeo> listarDistritos(String provincia);
	
}
