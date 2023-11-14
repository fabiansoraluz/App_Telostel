package com.dawii.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.dawii.dto.Carrito;
import com.dawii.dto.ReporteVentasDTO;
import com.dawii.dto.VentaDTO;
import com.dawii.entity.DetalleVenta;
import com.dawii.entity.DetalleVentaPK;

import com.dawii.entity.Venta;
import com.dawii.service.ProductoService;
import com.dawii.service.VentaService;
import com.dawii.utils.Mensaje;
import com.dawii.utils.ReporteGenerado;
import org.springframework.http.HttpHeaders;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/venta")
@CrossOrigin("http://localhost:4200")
public class VentaController {

	@Autowired
	private VentaService SVenta;

	@Autowired
	private ProductoService SProducto;

	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody Venta bean) {
		Venta venta = SVenta.grabar(bean);
		return new ResponseEntity<Venta>(venta, HttpStatus.CREATED);

	}

	@PostMapping("/detalle/{idVenta}")
	public ResponseEntity<?> registrarDetalle(@PathVariable Long idVenta, @RequestBody List<Carrito> lista) {
		for (Carrito carrito : lista) {

			// Creamos el detalleVenta
			DetalleVenta detalle = new DetalleVenta();
			detalle.setCantidad(carrito.getCantidad());
			detalle.setPrecio(carrito.getPrecio());

			// Generamos la PK
			DetalleVentaPK pk = new DetalleVentaPK();
			pk.setProducto(carrito.getIdProducto());
			pk.setVenta(idVenta);

			// Setteamos la PK
			detalle.setPk(pk);

			// Registramos el detalle
			SVenta.registrarDetalle(detalle);

			// Actualizamos el stock
			int stock_registrado = SProducto.buscar(carrito.getIdProducto()).getStock();
			int cantidad = carrito.getCantidad();
			int stock_actual = stock_registrado - cantidad;
			SProducto.actualizarStock(stock_actual, carrito.getIdProducto());

		}
		return new ResponseEntity<Mensaje>(new Mensaje("Venta Exitosa"), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<VentaDTO>> getVentasPorFecha() {
	    List<VentaDTO> ventasPorFecha = SVenta.listaVentasPorFecha();
	    return new ResponseEntity<>(ventasPorFecha, HttpStatus.OK);
	}

	@GetMapping("/consulta/{fecha}")
    public ResponseEntity<List<VentaDTO>> getTotalImporteByFecha(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<VentaDTO> result = SVenta.findTotalImporteByFecha(fecha);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	
	@GetMapping("/fecha/{fecha}")
    public ResponseEntity<byte[]> generarReportePorFecha2(@PathVariable String fecha) {
        try {
            LocalDate fechaConsulta = LocalDate.parse(fecha);
            List<ReporteVentasDTO> reporteVentasList = SVenta.ReportePorFecha(fechaConsulta);

            if (reporteVentasList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Generar el informe en formato PDF
            byte[] pdfBytes = ReporteGenerado.generateReportVentas(reporteVentasList);

            // Configurar las cabeceras de la respuesta para indicar el tipo de contenido
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "reporte_ventas.pdf");

            // Devolver la respuesta con el PDF y las cabeceras configuradas
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (JRException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
}
