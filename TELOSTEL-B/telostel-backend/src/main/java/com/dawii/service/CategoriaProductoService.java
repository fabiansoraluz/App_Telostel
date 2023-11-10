package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.ICategoriaProductoDAO;
import com.dawii.entity.CategoriaProducto;

@Service
public class CategoriaProductoService {
	@Autowired
	private ICategoriaProductoDAO repo;
	
	public List<CategoriaProducto> listar(){
		return repo.findAll();
	}
	
    public CategoriaProducto buscarPorId(Long id) {
        return repo.findByIdCategoria(id);
    }
}
