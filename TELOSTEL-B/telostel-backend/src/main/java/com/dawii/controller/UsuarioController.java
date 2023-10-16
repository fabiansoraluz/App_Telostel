package com.dawii.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.dto.JwtDto;
import com.dawii.dto.LoginUsuario;
import com.dawii.dto.RecuperarUsuario;
import com.dawii.dto.UsuarioPrincipal;
import com.dawii.entity.Empleado;
import com.dawii.entity.Rol;
import com.dawii.entity.Usuario;
import com.dawii.jwt.JwtProvider;
import com.dawii.service.EmpleadoService;
import com.dawii.service.UsuarioService;
import com.dawii.utils.Key;
import com.dawii.utils.Mensaje;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JavaMailSender mail;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UsuarioService SUsuario;
	
	@Autowired
	private EmpleadoService SEmpleado;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Value("{spring.mail.username}")
	private String correo_envio;
	
	@PostMapping("/mail/{email}")
	public ResponseEntity<?> enviarCorreo(@PathVariable String email){
		if(SUsuario.existeXCorreo(email)) {
			
			Key.generateKey();  
			SimpleMailMessage correo_entidad = new SimpleMailMessage();
			correo_entidad.setTo(email);
			correo_entidad.setFrom(correo_envio);
			correo_entidad.setSubject("Clave de Recuperación");
			correo_entidad.setText("Estimado: \n\nSe le envia su clave secreta para la recuperación de su cuenta: " + Key.getSecret_key());
			
			mail.send(correo_entidad);
			return new ResponseEntity<Mensaje>(new Mensaje("Correo enviado"),HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Correo no asociado a ninguna cuenta"),HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/recuperar")
	public ResponseEntity<?> recuperar(@Valid @RequestBody RecuperarUsuario bean ,HttpSession sesion,BindingResult result){
		if(result.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Error de validación"),HttpStatus.BAD_REQUEST);
		}
		if(Key.getSecret_key()== null) {
			return new ResponseEntity<Mensaje>(new Mensaje("Debes enviar un correo de recuperación"),HttpStatus.BAD_REQUEST);
		}
		String KEY = Key.getSecret_key();
		if(!KEY.equals(bean.getKey())) {
			return new ResponseEntity<Mensaje>(new Mensaje("Key incorrecta"),HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = SUsuario.buscarXCorreo(bean.getEmail());
		usuario.setPassword(encoder.encode(bean.getPassword()));
		SUsuario.grabar(usuario);
		
		Key.limpiar();
		return new ResponseEntity<Mensaje>(new Mensaje("Contraseña modificada"),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return new ResponseEntity<List<Usuario>>(SUsuario.listar(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> registar(@Valid @RequestBody Usuario user,BindingResult result){
		
		if(result.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Error de validación"),HttpStatus.BAD_REQUEST);
		}
		if(SEmpleado.existeXDni(user.getEmpleado().getDni())) {
			return new ResponseEntity<Mensaje>(new Mensaje("El DNI ya está registrado"),HttpStatus.BAD_REQUEST);
		}
		if(SEmpleado.existeXCelular(user.getEmpleado().getCelular())) {
			return new ResponseEntity<Mensaje>(new Mensaje("El celular ya está registrado"),HttpStatus.BAD_REQUEST);
		}
		if(SUsuario.existeXUsername(user.getUsername())) {
			return new ResponseEntity<Mensaje>(new Mensaje("El username ya está registrado"),HttpStatus.BAD_REQUEST);
		}
		if(SUsuario.existeXCorreo(user.getCorreo())) {
			return new ResponseEntity<Mensaje>(new Mensaje("El correo ya está registrado"),HttpStatus.BAD_REQUEST);
		}
		//Registramos al empleado
		Empleado e = SEmpleado.grabar(user.getEmpleado());
		
		//Setteamps el rol
		List<Rol> roles = new ArrayList<Rol>();
		
		Rol rol = new Rol();
		rol.setId(2);
		roles.add(rol);
		
		user.setRoles(roles);
		
		//Setteamos al empleado en usuario
		user.setEmpleado(e);
		//Encriptamos la contraseña
		user.setPassword(encoder.encode(user.getPassword()));
		
		//Registramos al usuario
		Usuario usuario = SUsuario.grabar(user);
		return new ResponseEntity<Usuario>(usuario,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario user,BindingResult result){
		try {
			if(result.hasErrors()) {
				return new ResponseEntity<Mensaje>(new Mensaje("Error de validación"),HttpStatus.BAD_REQUEST);
			}
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generarToken(authentication);
			UsuarioPrincipal userDetails = (UsuarioPrincipal)authentication.getPrincipal();
			JwtDto jwtDto = new JwtDto(jwt,userDetails.getNombre(),userDetails.getUsername(),userDetails.getAuthorities(),userDetails.getEnlaces());
			return new ResponseEntity<JwtDto>(jwtDto,HttpStatus.OK);
		}catch(AuthenticationException ex) {
			return new ResponseEntity<Mensaje>(new Mensaje("Usuario y/o contraseña incorrectos"),HttpStatus.UNAUTHORIZED);
		}
	} 
	
}
