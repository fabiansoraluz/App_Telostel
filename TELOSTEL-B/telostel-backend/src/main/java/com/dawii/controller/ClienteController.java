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

import com.dawii.dto.ConsultaCliente;
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
	
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody Cliente bean){
		if(SCliente.existeCelular(bean.getCelular())) {
			return new ResponseEntity<Mensaje>(new Mensaje("Celular ya registrado"),HttpStatus.BAD_REQUEST);
		}else if(SCliente.existeDni(bean.getDni())) {
			return new ResponseEntity<Mensaje>(new Mensaje("DNI ya registrado"),HttpStatus.BAD_REQUEST);
		}
		Cliente cliente = SCliente.grabar(bean);
		if(cliente!=null) {
			return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);	
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Error al registrar el cliente"),HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/consultar")
	public ResponseEntity<?> consultar(@RequestBody ConsultaCliente consulta){
		List<Cliente> bean = SCliente.consulta(consulta.getNombre(),consulta.getDni());
		if(bean.size()>0) {
			return new ResponseEntity<List<Cliente>>(bean,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No se encontró coincidencias"),HttpStatus.BAD_REQUEST);
	}
	@PutMapping
	public ResponseEntity<?> actualizar(@RequestBody Cliente bean){
		
		Cliente response = SCliente.buscar(bean.getId());
		
		if(SCliente.existeCelular(bean.getCelular()) && (!bean.getCelular().equals(response.getCelular()))) {
			return new ResponseEntity<Mensaje>(new Mensaje("Celular ya registrado"),HttpStatus.BAD_REQUEST);
		}else if(SCliente.existeDni(bean.getDni()) && (!bean.getDni().equals(response.getDni()))) {
			return new ResponseEntity<Mensaje>(new Mensaje("DNI ya registrado"),HttpStatus.BAD_REQUEST);
		}
		Cliente cliente = SCliente.grabar(bean);
		if(cliente!=null) {
			return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);	
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Error al actualizar el cliente"),HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable long id){
		try {
			SCliente.eliminar(id);
			return new ResponseEntity<Mensaje>(new Mensaje("El cliente fue eliminado"),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Mensaje>(new Mensaje("Error al eliminar el cliente"),HttpStatus.BAD_REQUEST);
		}
	}
}
