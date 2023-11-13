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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.entity.Habitacion;
import com.dawii.entity.TipoHabitacion;
import com.dawii.service.HabitacionService;
import com.dawii.utils.Mensaje;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/habitacion")
public class HabitacionController {
	
	@Autowired
	private HabitacionService SHabitacion;

	// METODOS TIPO HABITACION
	
	@GetMapping("/tipo")
    public ResponseEntity<?> listarTipos() {
        List<TipoHabitacion> tiposHabitacion = SHabitacion.listarTipo();
        return new ResponseEntity<List<TipoHabitacion>>(tiposHabitacion, HttpStatus.OK);
    }

    // Buscar un tipo de habitación por ID
    @GetMapping("/tipoid/{id}")
    public ResponseEntity<?> buscarPorIdTipo(@PathVariable Long id) {
        TipoHabitacion tipoHabitacion = SHabitacion.buscarPorId(id);
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
	
	
	// METODO PARA REGISTRAR UNA HABITACION
	@PostMapping("/registrar")
	public ResponseEntity<Habitacion> registrarHabitacion(@RequestBody Habitacion habitacion) {
	    Habitacion habitacionGuardada = SHabitacion.grabar(habitacion);
	    return new ResponseEntity<>(habitacionGuardada, HttpStatus.CREATED);
	}
	
	
	// METODO PARA ACTUALIZAR UNA HABITACION REGISTRADA
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Habitacion> actualizarHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacion) {
	    Habitacion habitacionActualizada = SHabitacion.buscar(id);
	    
	    if (habitacionActualizada == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    // Actualiza la habitación con los nuevos datos.
	    habitacionActualizada.setNumero(habitacion.getNumero());
	    habitacionActualizada.setPiso(habitacion.getPiso());
	    habitacionActualizada.setEstado(habitacion.getEstado());
	    habitacionActualizada.setTipo(habitacion.getTipo());

	    // Guarda la habitación actualizada.
	    habitacionActualizada = SHabitacion.grabar(habitacionActualizada);

	    return new ResponseEntity<>(habitacionActualizada, HttpStatus.OK);
	}
	
	
	// METODO PARA ELIMINAR UNA HABITACION REGISTRADA
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
