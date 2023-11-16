package com.dawii.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawii.dto.ConsultaReserva;
import com.dawii.entity.DetalleReservacion;
import com.dawii.entity.DetalleReservacionPK;
import com.dawii.entity.Reservacion;
import com.dawii.entity.Sede;
import com.dawii.entity.Servicio;
import com.dawii.service.ReservacionService;
import com.dawii.utils.Mensaje;
import com.dawii.utils.ReporteGenerado;

@RestController
@RequestMapping("/api/reservacion")
@CrossOrigin("http://localhost:4200")
public class ReservacionController {

	@Autowired
	private ReservacionService SReservacion;

	@GetMapping
	public ResponseEntity<?> listar() {
		List<Reservacion> lista = SReservacion.listarReservaciones();
		if (lista.size() > 0) {
			return new ResponseEntity<List<Reservacion>>(lista, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay reservaciones"), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable long id) {
		Reservacion bean = SReservacion.buscarXId(id);
		if (bean != null) {
			return new ResponseEntity<Reservacion>(bean, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Error al encontrar reservación"), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/datosEstadisticos")
    public List<Map<String, Object>> datosEstadisticos() {
        return SReservacion.obtenerDatosEstadisticos();
    }
	
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody Reservacion bean) {

		// Validar reservacion

		if (SReservacion.clienteConReserva(bean.getCliente().getId())) {
			return new ResponseEntity<Mensaje>(new Mensaje("El cliente ya tiene reservacion"), HttpStatus.BAD_REQUEST);
		}

		// Grabamos la reservacion
		Reservacion reserva = SReservacion.registrarReserva(bean);

		// Grabamos los detalles
		for (Servicio s : reserva.getServicios()) {
			Servicio servicio = SReservacion.buscarServicio(s.getId());

			// Creamos el detalle
			DetalleReservacion detalle = new DetalleReservacion();

			// Settemos detalle
			detalle.setCosto(servicio.getCosto());
			detalle.setDescripcion(servicio.getDescripcion());

			// Construimos el primary key
			DetalleReservacionPK pk = new DetalleReservacionPK();
			pk.setReservacion(reserva.getId());
			pk.setServicio(servicio.getId());

			// Setteamos la PK
			detalle.setPk(pk);

			SReservacion.registrarDetalle(detalle);
		}

		// Actualizamos el estado de la habitacion
		long idHabitacion = bean.getHabitacion().getId();
		SReservacion.actualizarEstadoHab("reservado", idHabitacion);

		return new ResponseEntity<Mensaje>(new Mensaje("Reservacion Exitosa"), HttpStatus.CREATED);
	}

	@GetMapping("/servicio")
	public ResponseEntity<?> listarServicios() {
		List<Servicio> lista = SReservacion.listarServicios();
		if (lista.size() > 0) {
			return new ResponseEntity<List<Servicio>>(lista, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay servicios registrados"), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/servicio/{id}")
	public ResponseEntity<?> buscarServicio(@PathVariable long id) {
		Servicio bean = SReservacion.buscarServicio(id);
		if (bean != null) {
			return new ResponseEntity<Servicio>(bean, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("Error al encontrar reservación"), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/sede")
	public ResponseEntity<?> listarSedes() {
		List<Sede> lista = SReservacion.listarSede();
		if (lista.size() > 0) {
			return new ResponseEntity<List<Sede>>(lista, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay sedes registradas"), HttpStatus.BAD_REQUEST);
	}

	// CONSULTAS
	
	@GetMapping("/reporteFec/{fecInicial}/{fecFinal}")
	public ResponseEntity<?> filtroReporte(@PathVariable LocalDate fecInicial, @PathVariable LocalDate fecFinal) {
		List<Reservacion> lista = SReservacion.FiltrarReservacionFechas(fecInicial, fecFinal);
		if (lista.size() > 0) {
			return new ResponseEntity<List<Reservacion>>(lista, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay reservaciones"), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/reporteFec2/{id}")
	public ResponseEntity<?> DetalleReporte(@PathVariable long id) {
		try {
			Reservacion reservacion = SReservacion.buscarXId(id);
			List<Reservacion> reservaciones = Collections.singletonList(reservacion);
			byte[] pdfReport = ReporteGenerado.generateReport(reservaciones);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("inline", "reporte.pdf");

			return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error al generar el informe", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/consulta")
	public ResponseEntity<?> consulta(@RequestBody ConsultaReserva bean){
		List<Reservacion> lista = SReservacion.consulta(bean.getNumero(), bean.getCheckIn(), bean.getCheckOut());
		if (lista.size() > 0) {
			return new ResponseEntity<List<Reservacion>>(lista, HttpStatus.OK);
		}
		return new ResponseEntity<Mensaje>(new Mensaje("No hay coincidencias"), HttpStatus.BAD_REQUEST);
	}


}
