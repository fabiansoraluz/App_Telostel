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

import com.dawii.entity.CargoEmpleado;
import com.dawii.entity.Empleado;
import com.dawii.service.EmpleadoService;
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/empleado")
@CrossOrigin("http://localhost:4200")
public class EmpleadoController {

	@Autowired
	private EmpleadoService SEmpleado;
	
	@GetMapping("/username/{username}")
	public ResponseEntity<?> buscarXUsername(@PathVariable String username){
		Empleado bean = SEmpleado.buscarXUsername(username);
		if(bean != null) {
			return new ResponseEntity<Empleado>(bean,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Empleado no encontrado"),HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/cargo")
	public ResponseEntity<?> listarCargos(){
		List<CargoEmpleado> lista = SEmpleado.listarCargos();
		if(lista.size()>0) {
			return new ResponseEntity<List<CargoEmpleado>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay cargos registrados"),HttpStatus.BAD_REQUEST);
	}
}
