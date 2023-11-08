package com.dawii.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.entity.DetalleReservacion;
import com.dawii.entity.DetalleReservacionPK;
import com.dawii.entity.Reservacion;
import com.dawii.entity.Sede;
import com.dawii.entity.Servicio;
import com.dawii.service.ReservacionService;
import com.dawii.utils.Mensaje;

@RestController
@RequestMapping("/api/reservacion")
@CrossOrigin("http://localhost:4200")
public class ReservacionController {

	@Autowired
	private ReservacionService SReservacion;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Reservacion> lista = SReservacion.listarReservaciones();
		if(lista.size()>0) {
			return new ResponseEntity<List<Reservacion>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay reservaciones"),HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable long id){
		Reservacion bean = SReservacion.buscarXId(id);
		if(bean!=null) {
			return new ResponseEntity<Reservacion>(bean,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Error al encontrar reservación"),HttpStatus.BAD_REQUEST);
	}
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody Reservacion bean) {
		
		//Validar reservacion
		
		if(SReservacion.clienteConReserva(bean.getCliente().getId())) {
			return new ResponseEntity<Mensaje>(new Mensaje("El cliente ya tiene reservacion"),HttpStatus.BAD_REQUEST);
		}
		
		//Grabamos la reservacion
		Reservacion reserva = SReservacion.registrarReserva(bean);
		
		//Grabamos los detalles
		for(Servicio s: reserva.getServicios()) {
			Servicio servicio = SReservacion.buscarServicio(s.getId());
			
			//Creamos el detalle
			DetalleReservacion detalle = new DetalleReservacion();
			
			//Settemos detalle
			detalle.setCosto(servicio.getCosto());
			detalle.setDescripcion(servicio.getDescripcion());
			
			//Construimos el primary key
			DetalleReservacionPK pk = new DetalleReservacionPK();
			pk.setReservacion(reserva.getId());
			pk.setServicio(servicio.getId());
			
			//Setteamos la PK
			detalle.setPk(pk);
			
			SReservacion.registrarDetalle(detalle);
		}
		
		//Actualizamos el estado de la habitacion
		long idHabitacion = bean.getHabitacion().getId();
		SReservacion.actualizarEstadoHab("reservado", idHabitacion);
		
		return new ResponseEntity<Mensaje>(new Mensaje("Reservacion Exitosa"),HttpStatus.CREATED);
	}
	
	@GetMapping("/servicio")
	public ResponseEntity<?> listarServicios(){
		List<Servicio> lista = SReservacion.listarServicios();
		if(lista.size()>0) {
			return new ResponseEntity<List<Servicio>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay servicios registrados"),HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/servicio/{id}")
	public ResponseEntity<?> buscarServicio(@PathVariable long id){
		Servicio bean = SReservacion.buscarServicio(id);
		if(bean!=null) {
			return new ResponseEntity<Servicio>(bean,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Error al encontrar reservación"),HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/sede")
	public ResponseEntity<?> listarSedes(){
		List<Sede> lista = SReservacion.listarSede();
		if(lista.size()>0) {
			return new ResponseEntity<List<Sede>>(lista,HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay sedes registradas"),HttpStatus.BAD_REQUEST);
	}
}