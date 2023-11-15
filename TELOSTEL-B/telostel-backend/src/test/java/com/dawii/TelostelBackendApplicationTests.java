package com.dawii;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class TelostelBackendApplicationTests {

	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	void contextLoads() {
		System.out.print(encoder.encode("Password25"));
	}

}
