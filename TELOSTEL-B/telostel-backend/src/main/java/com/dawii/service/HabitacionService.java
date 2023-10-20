package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IHabitacionDAO;
import com.dawii.entity.Habitacion;

@Service
public class HabitacionService {

	@Autowired
	private IHabitacionDAO repo;
	
	public Habitacion grabar(Habitacion bean) {
		return repo.save(bean);
	}
	
	public Habitacion actualizar(Habitacion bean) {
		return repo.save(bean);
	}
	
	public void eliminarporID(Long cod) {
		repo.deleteById(cod);
	}
	
	public Habitacion buscar(Long cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public List<Habitacion> listarTodos(){
		return repo.findAll();
	}
}
