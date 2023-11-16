package com.dawii.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dawii.entity.Producto;

import jakarta.transaction.Transactional;

public interface IProductoDAO extends JpaRepository<Producto, Long>{

	public List<Producto> findByNombreStartingWith(String nombre);
	
	@Transactional
	@Modifying
	@Query("UPDATE Producto P SET P.stock = ?1 WHERE P.id=?2")
	public void actualizarStock(int stock,long id);
	
	@Query("select p from Producto p where p.categoria.id = ?1")
	public List<Producto> productoXCategoria(Long id);

	@Query("SELECT p FROM Producto p WHERE p.nombre LIKE CONCAT(:nombre, '%') AND (:categoria = 0 OR p.categoria.id = :categoria)")
	public List<Producto> consulta(@Param("nombre") String nombre, @Param("categoria") long cateogira);
	
}
