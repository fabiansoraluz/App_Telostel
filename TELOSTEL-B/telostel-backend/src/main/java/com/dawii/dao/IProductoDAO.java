package com.dawii.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.Producto;

public interface IProductoDAO extends JpaRepository<Producto, Long>{

	public List<Producto> findByNombreStartingWith(String nombre);
	
}
