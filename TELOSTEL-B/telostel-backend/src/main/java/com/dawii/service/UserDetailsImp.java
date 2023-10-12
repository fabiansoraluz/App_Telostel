package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dawii.dto.UsuarioPrincipal;
import com.dawii.entity.Enlace;
import com.dawii.entity.Usuario;

@Service
public class UserDetailsImp implements UserDetailsService{

	@Autowired
	private UsuarioService SUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = SUsuario.buscarXUsername(username);
		List<Enlace> enlaces = user.getRoles().get(0).getEnlaces();
		return UsuarioPrincipal.build(user, enlaces);
	}

}
