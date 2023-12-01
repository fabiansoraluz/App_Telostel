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

import com.dawii.dto.HabitacionDTO;
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
    @GetMapping("/tipo/{id}")
    public ResponseEntity<?> buscarPorIdTipo(@PathVariable Long id) {
        TipoHabitacion tipoHabitacion = SHabitacion.buscarPorId(id);
        if (tipoHabitacion != null) {
            return new ResponseEntity<TipoHabitacion>(tipoHabitacion, HttpStatus.OK);
        }
        return new ResponseEntity<Mensaje>(new Mensaje("Tipo de habitación no encontrado"), HttpStatus.BAD_REQUEST);
    }

	
	//CRUD HABITACION
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
	
	// METODO PARA REGISTRAR UNA HABITACION
	@PostMapping
	public ResponseEntity<?> registrarHabitacion(@RequestBody Habitacion habitacion) {
		int numero = generarNumero(habitacion.getPiso(),habitacion.getHotel().getSede().getId());
		if(numero==-1) {
			return new ResponseEntity<Mensaje>(new Mensaje("Habitaciones en el piso completas"),HttpStatus.BAD_REQUEST);
		}
		habitacion.setNumero(numero);
	    Habitacion habitacionGuardada = SHabitacion.grabar(habitacion);
	    return new ResponseEntity<Habitacion>(habitacionGuardada, HttpStatus.CREATED);
	}
	private int generarNumero(String piso,long id) {
		String numero = "";
		List<Habitacion> lista = SHabitacion.buscarXPisoAndSede(piso,id);
		if(lista.size()==0) {
			numero = piso+"01";
		}else if(lista.size()<5) {
			numero = ((lista.get(lista.size()-1).getNumero())+1)+"";
		}else {
			return -1;
		}
		return Integer.parseInt(numero);
	}
	
	@PostMapping("/buscar")
	public ResponseEntity<?> buscarXPisoAndTipo(@RequestBody HabitacionDTO bean){
		List<Habitacion> lista = SHabitacion.buscarXSedeAndTipo(bean.getSede(), bean.getTipo());
		if(lista.size()>0) {
			return new ResponseEntity<List<Habitacion>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No se encontraron coincidencias"),HttpStatus.BAD_REQUEST);
	}
	
	// METODO PARA ACTUALIZAR UNA HABITACION REGISTRADA
	@PutMapping
	public ResponseEntity<?> actualizarHabitacion(@RequestBody Habitacion habitacion) {
	    // Guarda la habitación actualizada.
	    Habitacion bean = SHabitacion.grabar(habitacion);
	    return new ResponseEntity<Habitacion>(bean, HttpStatus.OK);
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
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<?> buscarXTipo(@PathVariable int tipo){
		List<Habitacion> lista = SHabitacion.buscarXTipo(tipo);
		if(lista != null) {
			return new ResponseEntity<List<Habitacion>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay habitaciones"),HttpStatus.BAD_REQUEST);
	}

	
}
