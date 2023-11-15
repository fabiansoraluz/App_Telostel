package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.CategoriaProducto;

public interface ICategoriaProductoDAO extends JpaRepository<CategoriaProducto, Long> {

}
