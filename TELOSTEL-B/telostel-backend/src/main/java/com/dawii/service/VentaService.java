package com.dawii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawii.dao.IDetalleVentaDAO;
import com.dawii.dao.IVentaDAO;
import com.dawii.entity.DetalleVenta;
import com.dawii.entity.Venta;

@Service
public class VentaService {
	
	@Autowired
	private IVentaDAO ventaDAO;
	@Autowired
	private IDetalleVentaDAO detalleDAO;
	
	//Registrar venta
	public Venta grabar(Venta bean) {
		return ventaDAO.save(bean);
	}
	
	//Registrar Detalle
	public void registrarDetalle(DetalleVenta detalle) {
		detalleDAO.save(detalle);
	}
	
	//Buscar Venta por ID
	public Venta buscarXId(Long id) {
		return ventaDAO.findById(id).orElse(null);
	}
}
