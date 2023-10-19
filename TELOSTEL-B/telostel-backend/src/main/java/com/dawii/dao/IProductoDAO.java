package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.Producto;

public interface IProductoDAO extends JpaRepository<Producto, Long>{

}
