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
import com.dawii.service.TipoHabitacionServices;
import com.dawii.utils.NotFoundException;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/habitacion")
public class HabitacionController {

	@Autowired
	private HabitacionService servicio;
	
	@Autowired
	private TipoHabitacionServices servicioTipoHab;
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Habitacion> registrar(@RequestBody Habitacion bean){
		Habitacion obj=servicio.grabar(bean);
		return new ResponseEntity<>(obj,HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Habitacion> actualizar(@RequestBody Habitacion bean){
		Habitacion hab=servicio.buscar(bean.getId());
		if(hab==null) {
			throw new NotFoundException();
		}
		else {
			hab=servicio.actualizar(bean); 
		}
		return new ResponseEntity<>(hab,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable("codigo") Long cod){
		Habitacion hab= servicio.buscar(cod);
		if(hab==null) {
			throw new NotFoundException();
		}else {
			servicio.eliminarporID(cod);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Habitacion>> listarTodos(){
		return new ResponseEntity<>(servicio.listarTodos(),HttpStatus.OK);
	}
	
	@GetMapping("listar/{codigo}")
	public ResponseEntity<Habitacion> buscarPorID(@PathVariable("codigo") Long cod){
		Habitacion hab=servicio.buscar(cod);
		if(hab==null) {
			throw new NotFoundException();
		}
		else {
			servicio.buscar(cod);
		}
		
		return new ResponseEntity<>(hab,HttpStatus.OK);	
	}
	
	
	/*LISTAR LOS TIPOS DE HABITACIONES*/
	@GetMapping("/tipos")
	public ResponseEntity<List<TipoHabitacion>> listarTipos(){
		return new ResponseEntity<>(servicioTipoHab.listar(),HttpStatus.OK);
	}
}























