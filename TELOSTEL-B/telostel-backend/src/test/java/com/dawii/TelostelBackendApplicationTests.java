package com.dawii;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dawii.dao.IProductoDAO;

@SpringBootTest
class TelostelBackendApplicationTests {

	@Autowired
	private IProductoDAO repo;
	
	@Test
	void contextLoads() {
		repo.actualizarStock(40,1);
	}

}
