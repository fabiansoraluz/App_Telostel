package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IClienteDAO;
import com.dawii.entity.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private IClienteDAO repo;
	
	public List<Cliente> listar(){
		return repo.listar();
	}
	public List<Cliente> buscarXNombre(String nombre){
		return repo.findByNombreStartingWith(nombre);
	}
	public Cliente buscar(long id) {
		return repo.findById(id).orElse(null);
	}
	
}
