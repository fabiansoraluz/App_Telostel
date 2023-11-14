package com.dawii.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dawii.dao.IDetalleVentaDAO;
import com.dawii.dao.IVentaDAO;
import com.dawii.dto.ReporteVentasDTO;
import com.dawii.dto.VentaDTO;
import com.dawii.entity.DetalleVenta;

import com.dawii.entity.Venta;

import javax.sql.DataSource;

@Service
public class VentaService {

	private final JdbcTemplate jdbcTemplate;

	public VentaService(DataSource dataSources) {
		this.jdbcTemplate = new JdbcTemplate(dataSources);
	}

	@Autowired
	private IVentaDAO ventaDAO;
	@Autowired
	private IDetalleVentaDAO detalleDAO;

	// Registrar venta
	public Venta grabar(Venta bean) {
		return ventaDAO.save(bean);
	}

	// Registrar Detalle
	public void registrarDetalle(DetalleVenta detalle) {
		detalleDAO.save(detalle);
	}

	// Buscar Venta por ID
	public Venta buscarXId(Long id) {
		return ventaDAO.findById(id).orElse(null);
	}

	public List<Venta> listar() {
		return ventaDAO.findAll();
	}

	@SuppressWarnings("deprecation")
	public List<ReporteVentasDTO> ReportePorFecha(LocalDate fecha) throws SQLException {
		String sql = "CALL generar_reporte_por_fecha(?)";

		// Utilizamos BeanPropertyRowMapper para mapear el resultado a ReporteVentasDTO
		List<ReporteVentasDTO> reporteVentasList = jdbcTemplate.query(sql, new Object[] { fecha },
				new BeanPropertyRowMapper<>(ReporteVentasDTO.class, false));

		return reporteVentasList;
	}

	public List<VentaDTO> listaVentasPorFecha() {
		return ventaDAO.findTotalImporteByFecha();
	}

	public List<VentaDTO> findTotalImporteByFecha(LocalDate fecha) {
		return ventaDAO.findTotalImporteByFecha(fecha);
	}

}
