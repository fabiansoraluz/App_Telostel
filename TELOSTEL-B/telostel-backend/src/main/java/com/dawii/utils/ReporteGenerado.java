package com.dawii.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import com.dawii.entity.Reservacion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteGenerado {

	public static byte[] generateReport(List<Reservacion> data) throws JRException {
		try {
			// Cargar el archivo .jasper desde el classpath
			InputStream jasperStream = ReporteGenerado.class.getResourceAsStream("/reporteReservas/ReporteReservas.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

			// Crear un mapa de parámetros (si es necesario)
			HashMap<String, Object> params = new HashMap<>();

			// Crear el informe con los datos proporcionados
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
					new JRBeanCollectionDataSource(data));

			// Exportar el informe a bytes (puedes ajustar el formato según tus necesidades)
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
