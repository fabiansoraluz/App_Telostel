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

import com.dawii.entity.CategoriaProducto;
import com.dawii.service.CategoriaProductoService;
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/categoriaproducto")
@CrossOrigin("http://localhost:4200")
public class CategoriaProductoController {

	@Autowired
	private CategoriaProductoService SCategoriaProducto;
	
	
	@GetMapping
	public ResponseEntity<?> listarCategorias(){
		List<CategoriaProducto> categorias = SCategoriaProducto.listar();
		if (!categorias.isEmpty()) {
			return new ResponseEntity<List<CategoriaProducto>>(categorias, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No se encontraron categorías de productos"), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarCategoriaXId(@PathVariable Long id){
		CategoriaProducto categoria = SCategoriaProducto.buscarPorId(id);
		if(categoria != null) {
			return new ResponseEntity<CategoriaProducto>(categoria, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Categoría de producto no encontrada"), HttpStatus.NOT_FOUND);
	}
}
