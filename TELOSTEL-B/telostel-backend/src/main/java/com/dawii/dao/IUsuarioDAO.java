package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
	public boolean existsByUsername(String username);
	
}
