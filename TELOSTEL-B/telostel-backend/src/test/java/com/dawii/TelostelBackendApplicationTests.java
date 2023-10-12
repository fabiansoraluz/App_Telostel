package com.dawii;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dawii.dao.IUbigeoDAO;

@SpringBootTest
class TelostelBackendApplicationTests {

	@Autowired
	private IUbigeoDAO repo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	void contextLoads() {
		System.out.println(repo.listarDistritos("Lima"));
		System.out.println(encoder.encode("test"));
	}

}
