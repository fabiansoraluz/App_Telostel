package com.dawii.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.entity.TipoHabitacion;
import com.dawii.service.TipoHabitacionService;
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/tipohabitacion")
@CrossOrigin("http://localhost:4200")
public class TipoHabitacionController {

	@Autowired
	private TipoHabitacionService STipoHabitacion;
	
	@GetMapping
    public ResponseEntity<?> listarTipos() {
        List<TipoHabitacion> tiposHabitacion = STipoHabitacion.listar();
        return new ResponseEntity<List<TipoHabitacion>>(tiposHabitacion, HttpStatus.OK);
    }

    // Buscar un tipo de habitación por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorIdTipo(@PathVariable Long id) {
        TipoHabitacion tipoHabitacion = STipoHabitacion.buscarPorId(id);
        if (tipoHabitacion != null) {
            return new ResponseEntity<TipoHabitacion>(tipoHabitacion, HttpStatus.OK);
        }
        return new ResponseEntity<Mensaje>(new Mensaje("Tipo de habitación no encontrado"), HttpStatus.BAD_REQUEST);
    }
}
