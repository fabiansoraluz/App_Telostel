package com.dawii;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dawii.dao.IClienteDAO;
import com.dawii.dao.IEmpleadoDAO;
import com.dawii.dao.IHabitacionDAO;
import com.dawii.dao.IReservacionDAO;
import com.dawii.entity.Servicio;

@SpringBootTest
class TelostelBackendApplicationTests {

	@Autowired
	private IClienteDAO repo;
	
	@Test
	void contextLoads() {
		System.out.println(repo.listar().size());
	}

}
