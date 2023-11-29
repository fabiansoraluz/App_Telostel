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
	
	public Cliente grabar(Cliente bean) {
		return repo.save(bean);
	}
	public void eliminar(long id) {
		repo.deleteById(id);
	}
	public List<Cliente> listar(){
		return repo.listar();
	}
	public List<Cliente> buscarXNombre(String nombre){
		return repo.findByNombreStartingWith(nombre);
	}
	public List<Cliente> consulta(String nombre, String dni){
	    return repo.consulta(nombre, dni);
	}
	public Cliente buscar(long id) {
		return repo.findById(id).orElse(null);
	}
	public Cliente buscarXDni(String dni) {
		return repo.findByDni(dni);
	}
	public boolean existeCelular(String celular) {
		return repo.existsByCelular(celular);
	}
	public boolean existeDni(String dni) {
		return repo.existsByDni(dni);
	}
}
