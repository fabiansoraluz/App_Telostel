package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.ITipoHabitacionDAO;
import com.dawii.entity.TipoHabitacion;

@Service
public class TipoHabitacionService {

	@Autowired
	private ITipoHabitacionDAO repo;
	
	public List<TipoHabitacion> listar(){
		return repo.findAll();
	}
	
    public TipoHabitacion buscarPorId(Long id) {
        return repo.findByIdTipoHabitacion(id);
    }
}
