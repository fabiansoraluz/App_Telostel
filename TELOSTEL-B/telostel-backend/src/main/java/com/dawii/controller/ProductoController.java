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

import com.dawii.dto.ConsultaProducto;
import com.dawii.entity.CategoriaProducto;
import com.dawii.entity.Producto;
import com.dawii.service.CategoriaService;
import com.dawii.service.ProductoService;
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin("http://localhost:4200")
public class ProductoController {
	
	@Autowired
	private ProductoService SProducto;
	
	@Autowired
	private CategoriaService SCategoria;

	// METODOS CATEGORIA PRODUCTO
	
	@GetMapping("/categorias")
	public ResponseEntity<?> listarCategorias(){
		List<CategoriaProducto> categorias = SCategoria.listar();
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
	
	@GetMapping
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
	
	@PostMapping("/consulta")
	public ResponseEntity<?> consultar(@RequestBody ConsultaProducto consulta){
		List<Producto> lista = SProducto.consulta(consulta.getNombre(),consulta.getIdCategoria());
		if(lista.size()>0) {
			return new ResponseEntity<List<Producto>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay coincidencias"),HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> buscarXNombre(@PathVariable String nombre){
		List<Producto> lista = SProducto.buscarXNombre(nombre);
		return new ResponseEntity<List<Producto>>(lista,HttpStatus.OK);
	}
	
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarProducto(@RequestBody Producto producto) {

	    // Verificar si ya existe un producto con el mismo nombre
	    List<Producto> productosConMismoNombre = SProducto.buscarXNombre(producto.getNombre());
	    if (!productosConMismoNombre.isEmpty()) {
	        return new ResponseEntity<Mensaje>(new Mensaje("Ya existe este Producto"), HttpStatus.BAD_REQUEST);
	    }

	    // Si no existe, registrar el nuevo producto
	    Producto productoRegistrado = SProducto.grabar(producto);
	    return new ResponseEntity<Producto>(productoRegistrado, HttpStatus.CREATED);
	}


	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
	    Producto productoExistente = SProducto.buscar(id);

	    if (productoExistente != null) {
	        // Verificar si existe otro producto con el mismo nombre
	        List<Producto> productosConMismoNombre = SProducto.buscarXNombre(producto.getNombre());
	        productosConMismoNombre.removeIf(p -> p.getId().equals(id)); // Excluir el producto actual

	        if (!productosConMismoNombre.isEmpty()) {
	            return new ResponseEntity<Mensaje>(new Mensaje("Ya existe un producto con el mismo nombre"), HttpStatus.BAD_REQUEST);
	        }

	        // Si no existe, proceder con la actualización
	        productoExistente.setNombre(producto.getNombre());
	        productoExistente.setCantUnidad(producto.getCantUnidad());
	        productoExistente.setPrecio(producto.getPrecio());
	        productoExistente.setStock(producto.getStock());
	        productoExistente.setCategoria(producto.getCategoria());

	        Producto productoActualizado = SProducto.grabar(productoExistente);
	        return new ResponseEntity<Producto>(productoActualizado, HttpStatus.OK);
	    }

	    return new ResponseEntity<Mensaje>(new Mensaje("Producto no encontrado"), HttpStatus.NOT_FOUND);
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
