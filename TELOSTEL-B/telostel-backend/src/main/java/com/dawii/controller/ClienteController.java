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

import com.dawii.entity.Cliente;
import com.dawii.service.ClienteService;
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("http://localhost:4200")
public class ClienteController {
	
	@Autowired
	private ClienteService SCliente;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Cliente> lista = SCliente.listar();
		if(lista.size()>0) {
			return new ResponseEntity<List<Cliente>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay clientes registrados"),HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> buscarXNombre(@PathVariable String nombre){
		List<Cliente> lista = SCliente.buscarXNombre(nombre);
		return new ResponseEntity<List<Cliente>>(lista,HttpStatus.OK);	
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable long id){
		Cliente bean = SCliente.buscar(id);
		if(bean!=null) {
			return new ResponseEntity<Cliente>(bean,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No se encontró al cliente"),HttpStatus.BAD_REQUEST);
	}
}
