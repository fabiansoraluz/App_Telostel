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

import com.dawii.entity.Ubigeo;
import com.dawii.service.UbigeoService;
import com.dawii.utils.Mensaje;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/ubigeo")
public class UbigeoController {
	
	@Autowired
	private UbigeoService SUbigeo;
	
	@GetMapping("/provincias")
	public ResponseEntity<?> listarProvincias(){
		return new ResponseEntity<List<String>>(SUbigeo.listarProvincias(),HttpStatus.OK);
	}
	
	@GetMapping("/distritos/{provincia}")
	public ResponseEntity<?> listarDistritos(@PathVariable String provincia){
		if(!provincia.isEmpty()) {
			return new ResponseEntity<List<Ubigeo>>(SUbigeo.listarDistritos(provincia),HttpStatus.OK);
		}else {
			return new ResponseEntity<Mensaje>(new Mensaje("Debes ingresar una provincia"),HttpStatus.BAD_REQUEST);
		}
	}
	
}
