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

import com.dawii.entity.CategoriaProducto;
import com.dawii.entity.Producto;
import com.dawii.service.ProductoService;
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin("http://localhost:4200")
public class ProductoController {
	
	@Autowired
	private ProductoService SProducto;
	
	// METODOS CATEGORIA PRODUCTO
	
	@GetMapping("/categorias")
	public ResponseEntity<?> listarCategorias(){
		List<CategoriaProducto> categorias = SProducto.listarCate();
		if (!categorias.isEmpty()) {
			return new ResponseEntity<List<CategoriaProducto>>(categorias, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No se encontraron categorías de productos"), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<?> buscarCategoriaXId(@PathVariable Long id){
		CategoriaProducto categoria = SProducto.buscarPorId(id);
		if(categoria != null) {
			return new ResponseEntity<CategoriaProducto>(categoria, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Categoría de producto no encontrada"), HttpStatus.NOT_FOUND);
	}
	
	
	//CRUD PRODUCTO
	
	@GetMapping("/productos")
	public ResponseEntity<?> listar(){
		return new ResponseEntity<List<Producto>>(SProducto.listar(),HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		Producto bean = SProducto.buscar(id);
		if(bean!=null) {
			return new ResponseEntity<Producto>(bean,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Producto no encontrado"),HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> buscarXNombre(@PathVariable String nombre){
		List<Producto> lista = SProducto.buscarXNombre(nombre);
		return new ResponseEntity<List<Producto>>(lista,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> registar(Producto bean) {
		Producto prod = SProducto.grabar(bean);
		return new ResponseEntity<Producto>(prod,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> actualizar(Producto bean) {
		Producto prod = SProducto.grabar(bean);
		return new ResponseEntity<Producto>(prod,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		Producto bean = SProducto.buscar(id);
		if(bean!=null) {
			SProducto.eliminar(id);
			return new ResponseEntity<Mensaje>(new Mensaje("Producto eliminado"),HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Producto no encontrado"),HttpStatus.BAD_REQUEST); 
	}
	
}
