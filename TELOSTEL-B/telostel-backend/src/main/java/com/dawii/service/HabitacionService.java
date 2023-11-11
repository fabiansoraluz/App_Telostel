package com.dawii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IHabitacionDAO;
import com.dawii.dao.ITipoHabitacionDAO;
import com.dawii.entity.Habitacion;
import com.dawii.entity.TipoHabitacion;

@Service
public class HabitacionService {
	
	@Autowired
	private IHabitacionDAO repo;
	
	@Autowired
	private ITipoHabitacionDAO repoTipo;
	
	public List<TipoHabitacion> listarTipo(){
		return repoTipo.findAll();
	}
	
    public TipoHabitacion buscarPorId(Long id) {
        return repoTipo.findByIdTipoHabitacion(id);
    }
	
	//CRUD
	public Habitacion buscar(Long id) {
		return repo.findById(id).orElse(null);
	}
	public List<Habitacion> listar(){
		return repo.findAll();
	}
	public Habitacion grabar(Habitacion bean) {
		return repo.save(bean);
	}
	public void eliminar(Long id) {
		repo.deleteById(id);
	}
	
	//CONSULTAS PERSONALIZADAS
	public int buscarUltimoPiso() {
		return repo.buscarUltimoPiso();
	}
	public List<Habitacion> buscarXPiso(int piso){
		return repo.findByPiso(piso);
	}
	public List<Habitacion> buscarXTipo(int id){
		return repo.buscarXTipo(id);
	}
}
