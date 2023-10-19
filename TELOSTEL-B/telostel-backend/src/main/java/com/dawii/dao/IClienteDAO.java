package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente, Long>{

}
