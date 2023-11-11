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

import com.dawii.entity.Distrito;
import com.dawii.service.UbigeoService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/ubigeo")
public class UbigeoController {
	
	@Autowired
	private UbigeoService SUbigeo;
	
	@GetMapping("/departamento")
	public ResponseEntity<?> listarDepartamentos(){
		return new ResponseEntity<List<String>>(SUbigeo.listarDep(),HttpStatus.OK);
	}
	
	@GetMapping("/provincia/{departamento}")
	public ResponseEntity<?> listarProvincia(@PathVariable String departamento){
		return new ResponseEntity<List<String>>(SUbigeo.listarProvXDep(departamento),HttpStatus.OK);
	}
	
	@GetMapping("/distrito/{provincia}")
	public ResponseEntity<?> listarDistritos(@PathVariable String provincia){
		return new ResponseEntity<List<Distrito>>(SUbigeo.listarDisXProv(provincia),HttpStatus.OK);
	}
	
}
