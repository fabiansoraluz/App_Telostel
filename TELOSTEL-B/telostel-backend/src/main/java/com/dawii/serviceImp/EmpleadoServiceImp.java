package com.dawii.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dawii.dao.IEmpleadoDAO;
import com.dawii.entity.Empleado;

@Service
public class EmpleadoServiceImp extends ICRUDImpl<Empleado, Long> {

	@Autowired
	private IEmpleadoDAO repo;
	
	@Override
	public JpaRepository<Empleado, Long> getRepository() {
		return repo;
	}

}
