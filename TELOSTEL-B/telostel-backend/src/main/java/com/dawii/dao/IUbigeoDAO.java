package com.dawii.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dawii.entity.Distrito;
import com.dawii.entity.Ubigeo;

@Repository
public interface IUbigeoDAO extends JpaRepository<Ubigeo, Long>{
	
	//Listar departamentos
	@Query("SELECT DISTINCT u.departamento FROM Ubigeo u")
	List<String> listarDepartamentos();
	
	//Listar provincias por departamento
	@Query("SELECT DISTINCT u.provincia FROM Ubigeo u WHERE u.departamento=?1")
	List<String> listarProvinciasXDepartamento(String departamento);
		
	//Listar distritos por provincia
	@Query("SELECT new com.dawii.entity.Distrito(u.id, u.distrito) FROM Ubigeo u WHERE u.provincia=?1")
	List<Distrito> listarDistritosXProvincia(String provincia);
	
}
