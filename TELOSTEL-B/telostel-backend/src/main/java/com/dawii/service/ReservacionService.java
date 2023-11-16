package com.dawii.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dawii.dao.IDetalleReservaDAO;
import com.dawii.dao.IHabitacionDAO;
import com.dawii.dao.IReservacionDAO;
import com.dawii.dao.ISedeDAO;
import com.dawii.dao.IServicioDAO;
import com.dawii.entity.DetalleReservacion;
import com.dawii.entity.Reservacion;
import com.dawii.entity.Sede;
import com.dawii.entity.Servicio;

import jakarta.transaction.Transactional;

@Service
public class ReservacionService {
	
	@Autowired
	private IReservacionDAO reservacionDAO;
	
	@Autowired
	private IDetalleReservaDAO detalleReservaDAO;
	
	@Autowired
	private IServicioDAO servicioDAO;
	
	@Autowired
	private ISedeDAO sedeDAO;
	
	@Autowired
	private IHabitacionDAO habitacionDAO;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> obtenerDatosEstadisticos() {
	    String sql = "CALL SP_NUMERO_RESERVACIONES()";
	    return jdbcTemplate.queryForList(sql);
	}
	
	public List<Reservacion> listarReservaciones(){
		return reservacionDAO.findAll();
	}
	public List<Servicio> listarServicios(){
		return servicioDAO.findAll();
	}
	public List<Sede> listarSede(){
		return sedeDAO.findAll();
	}
	@Transactional
	public Reservacion registrarReserva(Reservacion r) {
		return reservacionDAO.save(r);
	}
	public DetalleReservacion registrarDetalle(DetalleReservacion detalle) {
		return detalleReservaDAO.save(detalle);
	}
	public Reservacion buscarXId(long id) {
		return reservacionDAO.findById(id).orElse(null);
	}
	public Servicio buscarServicio(long id) {
		return servicioDAO.findById(id).orElse(null);
	}
	public Boolean clienteConReserva(long id) {
		return reservacionDAO.clienteConReserva(id);
	}
	
	public int actualizarEstadoHab(String estado,long id) {
		return habitacionDAO.actualizarEstado(estado, id);
	}
	
	public List<Reservacion> FiltrarReservacionFechas(LocalDate fecInicial, LocalDate fecFinal){
		return reservacionDAO.FiltrarReservacionFechas(fecInicial, fecFinal);
	}
	
	public List<Reservacion> consulta(int numero,LocalDate checkIn,LocalDate checkOut) {
		return reservacionDAO.consulta(numero, checkIn, checkOut);
	}
}
