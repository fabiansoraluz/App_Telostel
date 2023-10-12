package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IUsuarioDAO;
import com.dawii.entity.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private IUsuarioDAO repo;
	
	public boolean existeXCorreo(String correo) {
		return repo.existsByCorreo(correo);
	}
	public boolean existeXUsername(String username) {
		return repo.existsByUsername(username);
	}
	public Usuario buscarXUsername(String username) {
		return repo.findByUsername(username);
	}
	public Usuario buscarXCorreo(String correo) {
		return repo.findByCorreo(correo);
	}
	public Usuario grabar(Usuario user) {
		return repo.save(user);
	}
	public List<Usuario> listar(){
		return repo.findAll();
	}
	
}
