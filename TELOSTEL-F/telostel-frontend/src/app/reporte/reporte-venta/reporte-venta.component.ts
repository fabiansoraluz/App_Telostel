import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { VentaService } from 'src/app/services/venta.service';

@Component({
  selector: 'app-reporte-venta',
  templateUrl: './reporte-venta.component.html',
  styleUrls: ['./reporte-venta.component.css']
})
export class ReporteVentaComponent implements OnInit{

  ventas:any[]=[]
  error_lista:String
  fechaConsulta: string;


  constructor(
    private SVenta:VentaService
  ){}

  ngOnInit(): void {
    //Listamos las ventas
    this.listar()
  }

  listar(){
    this.SVenta.listaVenta().subscribe(
      (response) => this.ventas=response,
      (err)=> this.error_lista=err.error.mensaje
    )
  }

  ver(fecha:String){
    this.SVenta.generarReportePorFecha(fecha).subscribe(
      pdfSrc  => {
        // Abrir el PDF en una nueva pestaña
        const blobUrl = URL.createObjectURL(pdfSrc);
        window.open(blobUrl, '_blank');
      },
      error => {
        console.error('Error al obtener el detalle del reporte:', error);
      }
    )
  }

  consultar() {
    if (this.fechaConsulta) {
      this.SVenta.consultaVenta(this.fechaConsulta).subscribe(
        (response) => {
          console.log(response)
          this.ventas=response
        },
        (error) => {
          console.error('Error al realizar la consulta:', error);
        }
      );
    }
  }

  limpiar(){

    this.listar();
  }
}
