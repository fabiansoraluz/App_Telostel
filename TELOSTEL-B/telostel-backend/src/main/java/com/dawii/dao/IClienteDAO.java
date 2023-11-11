package com.dawii.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dawii.entity.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente, Long>{

	@Query("SELECT C FROM Cliente C WHERE C.estado=1")
	public List<Cliente> listar();
	@Query("SELECT c FROM Cliente c WHERE c.nombre LIKE CONCAT(:nombre, '%') AND (:dni = '' OR c.dni = :dni)")
	public List<Cliente> consulta(@Param("nombre") String nombre, @Param("dni") String dni);
	public List<Cliente> findByNombreStartingWith(String nombre);
	public boolean existsByCelular(String celular);
	public boolean existsByDni(String dni);
}
