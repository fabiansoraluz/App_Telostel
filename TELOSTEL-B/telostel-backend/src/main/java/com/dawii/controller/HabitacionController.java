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
import com.dawii.service.HabitacionService;
import com.dawii.utils.Mensaje;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/habitacion")
public class HabitacionController {

	@Autowired
	private HabitacionService SHabitacion;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return new ResponseEntity<List<Habitacion>>(SHabitacion.listar(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
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
	
}
