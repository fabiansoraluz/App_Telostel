package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.CargoEmpleado;

public interface ICargoDAO extends JpaRepository<CargoEmpleado, Long>{

}
