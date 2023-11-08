package com.dawii.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.dto.Carrito;
import com.dawii.entity.DetalleVenta;
import com.dawii.entity.DetalleVentaPK;
import com.dawii.entity.Venta;
import com.dawii.service.ProductoService;
import com.dawii.service.VentaService;
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/venta")
@CrossOrigin("http://localhost:4200")
public class VentaController {
	
	@Autowired
	private VentaService SVenta;
	
	@Autowired
	private ProductoService SProducto;
	
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody Venta bean){
		Venta venta = SVenta.grabar(bean);	
		return new ResponseEntity<Venta>(venta,HttpStatus.CREATED);
		
	}
	@PostMapping("/detalle/{idVenta}")
	public ResponseEntity<?> registrarDetalle(@PathVariable Long idVenta,@RequestBody List<Carrito> lista){		
		for(Carrito carrito:lista) {
			
			//Creamos el detalleVenta
			DetalleVenta detalle = new DetalleVenta();
			detalle.setCantidad(carrito.getCantidad());
			detalle.setPrecio(carrito.getPrecio());
			
			//Generamos la PK
			DetalleVentaPK pk = new DetalleVentaPK();
			pk.setProducto(carrito.getIdProducto());
			pk.setVenta(idVenta);
			
			//Setteamos la PK
			detalle.setPk(pk);
			
			//Registramos el detalle
			SVenta.registrarDetalle(detalle);
			
			//Actualizamos el stock
			int stock_registrado = SProducto.buscar(carrito.getIdProducto()).getStock();
			int cantidad = carrito.getCantidad();
			int stock_actual = stock_registrado - cantidad;
			SProducto.actualizarStock(stock_actual,carrito.getIdProducto());
			
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Venta Exitosa"),HttpStatus.CREATED);
	}
}
