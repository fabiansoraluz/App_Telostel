import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Reservacion } from 'src/app/model/reservacion';
import { ReservacionService } from 'src/app/services/reservacion.service';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-reporte-reservacion',
  templateUrl: './reporte-reservacion.component.html',
  styleUrls: ['./reporte-reservacion.component.css']
})
export class ReporteReservacionComponent {

  pdfSrc


  reservas: Reservacion [] = [];
  minFechaFinal: string = ''; 

  constructor(
    private sReservacion: ReservacionService,
    private router: Router
  ){}

  setMinFechaFinal(event: any) {
    this.minFechaFinal = event.target.value;
  }

  isFechaFinalValid(): boolean {
    const fechaInicial = (document.getElementById('fechaInicial') as HTMLInputElement).value;
    const fechaFinal = (document.getElementById('fechaFinal') as HTMLInputElement).value;

    if (fechaInicial === '' || fechaFinal === '') {
      return false;
    }

    const dateInicial = new Date(fechaInicial);
    const dateFinal = new Date(fechaFinal);

    return dateInicial < dateFinal;
  }



  generarReporte() {
    // Obtén las fechas desde los campos del formulario
    const fechaInicial = (document.getElementById('fechaInicial') as HTMLInputElement).value;
    const fechaFinal = (document.getElementById('fechaFinal') as HTMLInputElement).value;

    console.log('Fecha Inicial:', fechaInicial);
    console.log('Fecha Final:', fechaFinal);

    // Validación de fechas
    if (!this.isFechaFinalValid()) {
      Swal.fire('Fecha Incorrecta', 'Por favor, verifica el orden de las fechas para generar reporte.', 'error');
      return;
    }

    // Llama al servicio para obtener el reporte
    this.sReservacion.reporte1(fechaInicial, fechaFinal).subscribe(
      data => {
        console.log('Reporte generado:', data);

        // Verifica si hay reservas
        if (data && data.length > 0) {
          this.reservas = data;
        } else {
          // No hay reservaciones
        }
      },
      error => {
        console.error('Error al generar el reporte:', error);
      }
    );
  }

  


  verReserva(id: number) {
    this.sReservacion.reporteDetalle(id).subscribe(
      pdfSrc  => {
        // Abrir el PDF en una nueva pestaña
        const blobUrl = URL.createObjectURL(pdfSrc);
        window.open(blobUrl, '_blank');
      },
      error => {
        console.error('Error al obtener el detalle del reporte:', error);
      }
    );
  }
  




}
