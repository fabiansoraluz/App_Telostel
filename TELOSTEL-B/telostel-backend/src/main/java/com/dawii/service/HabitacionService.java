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
	public List<Habitacion> buscarXPisoAndSede(String piso,long idSede){
		return repo.buscarXPisoAndSede(piso,idSede);
	}
	public List<Habitacion> buscarXTipo(int id){
		return repo.buscarXTipo(id);
	}
	

	// AUTOCOMPLETADO PISO - NUMERO
	public Habitacion autocompletarNumeroHabitacion(String piso) {
	    // Encontrar la última habitación registrada en el piso seleccionado.
	    Habitacion ultimaHabitacionEnPiso = repo.findFirstByPisoOrderByNumeroDesc(piso);

	    // Si no se encontró ninguna habitación en ese piso, comienza desde el número 1.
	    int numero = (ultimaHabitacionEnPiso != null) ? ultimaHabitacionEnPiso.getNumero() : 0;

	    // Calcula el siguiente número basado en el piso.
	    int numeroSiguiente = Integer.parseInt(piso) * 100 + numero + 1;

	    Habitacion nuevaHabitacion = new Habitacion();
	    nuevaHabitacion.setPiso(piso);
	    nuevaHabitacion.setNumero(numeroSiguiente);

	    return nuevaHabitacion;

	}
	
	//Listar por sede y tipo de habitación
	public List<Habitacion> buscarXSedeAndTipo(long sede,String tipo){
		return repo.buscarXSedeAndTipo(sede, tipo);
	}
	
}
