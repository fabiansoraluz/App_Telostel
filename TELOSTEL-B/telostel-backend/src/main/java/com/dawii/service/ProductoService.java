package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IProductoDAO;
import com.dawii.entity.Producto;

@Service
public class ProductoService {
	
	@Autowired
	private IProductoDAO repo;
	
	//CRUD
	public Producto grabar(Producto bean) {
		return repo.save(bean);
	}
	public List<Producto> listar(){
		return repo.findAll();
	}
	public Producto buscar(Long id) {
		return repo.findById(id).orElse(null);
	}
	public void eliminar(Long id) {
		repo.deleteById(id);
	}
	
	//CONSULTAS
	public List<Producto> buscarXNombre(String nombre){
		return repo.findByNombreStartingWith(nombre);
	}
	
}
