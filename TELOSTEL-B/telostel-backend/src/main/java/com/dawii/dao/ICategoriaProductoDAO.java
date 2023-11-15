package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dawii.entity.CategoriaProducto;

public interface ICategoriaProductoDAO extends JpaRepository<CategoriaProducto, Long>{
	
    // Obtener una categoría por ID
    @Query("SELECT c FROM CategoriaProducto c WHERE c.id = :idCategoria")
    CategoriaProducto findByIdCategoria(@Param("idCategoria") Long idCategoria);

}
