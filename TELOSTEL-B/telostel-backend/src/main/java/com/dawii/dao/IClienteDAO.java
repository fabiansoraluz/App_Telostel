package com.dawii.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawii.entity.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente, Long>{

	public List<Cliente> findByNombreStartingWith(String nombre);
	
	@Query("SELECT C FROM Cliente C WHERE C.estado=1")
	public List<Cliente> listar();
	
}
