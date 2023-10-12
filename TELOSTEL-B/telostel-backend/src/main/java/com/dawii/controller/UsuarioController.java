package com.dawii.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.dto.JwtDto;
import com.dawii.dto.LoginUsuario;
import com.dawii.dto.UsuarioPrincipal;
import com.dawii.entity.Empleado;
import com.dawii.entity.Usuario;
import com.dawii.jwt.JwtProvider;
import com.dawii.service.EmpleadoService;
import com.dawii.service.UsuarioService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UsuarioService SUsuario;
	
	@Autowired
	private EmpleadoService SEmpleado;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return new ResponseEntity<List<Usuario>>(SUsuario.listar(),HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registar(@RequestBody Usuario user){
		
		//Registramos al empleado
		Empleado e = SEmpleado.grabar(user.getEmpleado());
		
		//Setteamos al empleado en usuario
		user.setEmpleado(e);
		//Encriptamos la contraseña
		user.setPassword(encoder.encode(user.getPassword()));
		
		//Registramos al usuario
		Usuario usuario = SUsuario.grabar(user);
		return new ResponseEntity<Usuario>(usuario,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginUsuario user){
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generarToken(authentication);
		UsuarioPrincipal userDetails = (UsuarioPrincipal)authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities(),userDetails.getEnlaces());
		return new ResponseEntity<JwtDto>(jwtDto,HttpStatus.OK);
	} 
	
}
