package com.dawii.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IEmpleadoDAO;

@Service
public class EmpleadoService {
	
	@Autowired
	private IEmpleadoDAO repo;
	
	//VALIDACIONES
	public boolean existeXDni(String dni) {
		return repo.existsByDni(dni);
	}
	public boolean existeXCelular(String celular) {
		return repo.existsByCelular(celular);
	}
	
	public boolean existeOtroXDni(Long id, String dni) {
        return repo.existsByDniAndIdNot(dni, id);
    }

    public boolean existeOtroXCelular(Long id, String celular) {
        return repo.existsByCelularAndIdNot(celular, id);
    }
	
}
