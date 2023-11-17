package com.dawii.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.ICargoDAO;
import com.dawii.dao.IEmpleadoDAO;
import com.dawii.entity.CargoEmpleado;
import com.dawii.entity.Empleado;

@Service
public class EmpleadoService {
	
	@Autowired
	private IEmpleadoDAO repo;
	
	@Autowired
	private ICargoDAO cargoDAO;
	
	//VALIDACIONES
	public boolean existeXDni(String dni) {
		return repo.existsByDni(dni);
	}
	public boolean existeXCelular(String celular) {
		return repo.existsByCelular(celular);
	}
	
	//CRUD
	public Empleado grabar(Empleado bean) {
		return repo.save(bean);
	}
	public Empleado buscarXId(Long id) {
		return repo.findById(id).orElse(null);
	}
	public List<Empleado> listarEmpleados(){
		return repo.findAll();
	}
	public void eliminar(Long id) {
		repo.deleteById(id);
	}
	public Empleado buscarXUsername(String username) {
		return repo.findXUsername(username);
	}
	public boolean existeOtroXDni(Long id, String dni) {
	    return repo.existsByDniAndIdNot(dni, id);
	 }
	public boolean existeOtroXCelular(Long id, String celular) {
	    return repo.existsByCelularAndIdNot(celular, id);
	}
	public List<CargoEmpleado> listarCargos(){
		return cargoDAO.findAll();
	}
	

}
