package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.DetalleReservacionPK;
import com.dawii.entity.DetalleVenta;

public interface IDetalleVentaDAO extends JpaRepository<DetalleVenta,DetalleReservacionPK>{

}
