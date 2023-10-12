package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.Empleado;

public interface IEmpleadoDAO extends JpaRepository<Empleado, Long>{

}
