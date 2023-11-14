package com.dawii.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dawii.dto.VentaDTO;
import com.dawii.entity.Venta;

public interface IVentaDAO extends JpaRepository<Venta, Long> {

	@Query("SELECT new com.dawii.dto.VentaDTO(v.createAt AS fecha, SUM(v.importe) AS importeTotal) FROM Venta v GROUP BY v.createAt")
	public List<VentaDTO> findTotalImporteByFecha();

	@Query("SELECT new com.dawii.dto.VentaDTO(v.createAt, SUM(v.importe)) FROM Venta v WHERE v.createAt = :fecha GROUP BY v.createAt")
	List<VentaDTO> findTotalImporteByFecha(@Param("fecha") LocalDate fecha);
}
