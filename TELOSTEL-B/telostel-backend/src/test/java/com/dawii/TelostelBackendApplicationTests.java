package com.dawii;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dawii.dao.ISedeDAO;

@SpringBootTest
class TelostelBackendApplicationTests {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ISedeDAO repo;
	
	@Test
	void contextLoads() {
		System.out.print(encoder.encode("Password25"));
		System.out.println(repo.listarSedes());
	}

}
