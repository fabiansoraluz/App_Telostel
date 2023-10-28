package com.dawii.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawii.entity.DetalleReservacion;
import com.dawii.entity.DetalleReservacionPK;

public interface IDetalleReservaDAO extends JpaRepository<DetalleReservacion, DetalleReservacionPK> {

}
