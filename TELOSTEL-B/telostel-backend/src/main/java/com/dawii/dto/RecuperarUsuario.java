package com.dawii.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecuperarUsuario {

	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String key;
	@NotEmpty
	private String password;
}
