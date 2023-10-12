package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.Empleado;

public interface IEmpleadoDAO extends JpaRepository<Empleado, Long>{

	//Buscar si existe empleado por celular
	public boolean existsByCelular(String celular);
	//Buscar si existe empleado por DNI
	public boolean existsByDni(String dni);
	
	
}
