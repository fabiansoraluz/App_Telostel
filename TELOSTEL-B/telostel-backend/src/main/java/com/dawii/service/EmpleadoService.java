package com.dawii.service;

import java.util.List;

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
	
    public boolean existeOtroXDni(Long id, String dni) {
        return repo.existsByDniAndIdNot(dni, id);
    }

    public boolean existeOtroXCelular(Long id, String celular) {
        return repo.existsByCelularAndIdNot(celular, id);
    }

    public Empleado buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Empleado actualizar(Empleado empleado) {
        return repo.save(empleado);
    }
	
}
