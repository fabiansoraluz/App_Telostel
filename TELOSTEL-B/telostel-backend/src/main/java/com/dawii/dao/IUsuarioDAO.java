package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long>{

	//Buscar por username
	public Usuario findByUsername(String username);
	//Buscar por correo
	public Usuario findByCorreo(String correo);
	//Buscar si existe usuario por username
	public boolean existsByUsername(String username);
	//Buscar si existe usuario por correo
	public boolean existsByCorreo(String correo);
	
}
