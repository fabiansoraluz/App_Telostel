package com.dawii.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.entity.Habitacion;
import com.dawii.entity.TipoHabitacion;
import com.dawii.service.HabitacionService;
import com.dawii.service.TipoHabitacionService;
import com.dawii.utils.Mensaje;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/habitacion")
public class HabitacionController {

	@Autowired
	private HabitacionService SHabitacion;
	
	@Autowired
	private TipoHabitacionService STipoHabitacion;

	// METODOS TIPO HABITACION
	
	@GetMapping("/tipo")
    public ResponseEntity<?> listarTipos() {
        List<TipoHabitacion> tiposHabitacion = STipoHabitacion.listar();
        return new ResponseEntity<List<TipoHabitacion>>(tiposHabitacion, HttpStatus.OK);
    }

    // Buscar un tipo de habitación por ID
    @GetMapping("/tipoid/{id}")
    public ResponseEntity<?> buscarPorIdTipo(@PathVariable Long id) {
        TipoHabitacion tipoHabitacion = STipoHabitacion.buscarPorId(id);
        if (tipoHabitacion != null) {
            return new ResponseEntity<TipoHabitacion>(tipoHabitacion, HttpStatus.OK);
        }
        return new ResponseEntity<Mensaje>(new Mensaje("Tipo de habitación no encontrado"), HttpStatus.BAD_REQUEST);
    }

	
	//CRUD HABITACION
	@GetMapping("/habitaciones")
	public ResponseEntity<?> listar(){
		return new ResponseEntity<List<Habitacion>>(SHabitacion.listar(),HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		Habitacion bean = SHabitacion.buscar(id);
		if(bean!=null) {
			return new ResponseEntity<Habitacion>(bean,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Habitacion no encontrado"),HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	public ResponseEntity<?> registar(Habitacion bean) {
		Habitacion prod = SHabitacion.grabar(bean);
		return new ResponseEntity<Habitacion>(prod,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> actualizar(Habitacion bean) {
		Habitacion prod = SHabitacion.grabar(bean);
		return new ResponseEntity<Habitacion>(prod,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		Habitacion bean = SHabitacion.buscar(id);
		if(bean!=null) {
			SHabitacion.eliminar(id);
			return new ResponseEntity<Mensaje>(new Mensaje("Habitación eliminada"),HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Habitacion no encontrado"),HttpStatus.BAD_REQUEST); 
	}
	
	//CONSULTAS PERSONALIZADAS
	@GetMapping("/ultimoPiso")
	public ResponseEntity<?> ultimoPiso(){
		int piso = SHabitacion.buscarUltimoPiso();
		return new ResponseEntity<Integer>(piso,HttpStatus.OK);
	}
	@GetMapping("/piso/{piso}")
	public ResponseEntity<?> buscarXPiso(@PathVariable int piso){
		List<Habitacion> lista = SHabitacion.buscarXPiso(piso);
		if(lista != null) {
			return new ResponseEntity<List<Habitacion>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay habitaciones"),HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<?> buscarXTipo(@PathVariable int tipo){
		List<Habitacion> lista = SHabitacion.buscarXTipo(tipo);
		if(lista != null) {
			return new ResponseEntity<List<Habitacion>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay habitaciones"),HttpStatus.BAD_REQUEST);
	}

	
}
