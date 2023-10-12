package com.dawii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IEmpleadoDAO;
import com.dawii.entity.Empleado;

@Service
public class EmpleadoService {
	
	@Autowired
	private IEmpleadoDAO repo;
	
	public Empleado grabar(Empleado bean) {
		return repo.save(bean);
	}
	public boolean existeXDni(String dni) {
		return repo.existsByDni(dni);
	}
	public boolean existeXCelular(String celular) {
		return repo.existsByCelular(celular);
	}
	
}
