import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ConsultaReservacion } from 'src/app/model/consulta-reservacion';
import { Reservacion } from 'src/app/model/reservacion';
import { ReservacionService } from 'src/app/services/reservacion.service';

@Component({
  selector: 'app-consulta-reservacion',
  templateUrl: './consulta-reservacion.component.html',
  styleUrls: ['./consulta-reservacion.component.css']
})
export class ConsultaReservacionComponent implements OnInit{

  // Paginación
  public pageSize = 5
  public desde = 0
  public hasta = 5

  public reservaciones:Reservacion[]
  public mensaje:String
  public consulta = new ConsultaReservacion()

  constructor(
    private SReservacion:ReservacionService
  ){}
  ngOnInit(): void {
    //Listar Reservaciones
    this.listarReservas()
  }
  private listarReservas(){
    this.SReservacion.listar().subscribe(
      (response)=>{this.reservaciones=response,this.mensaje=null},
      (err)=>this.mensaje=err.error.mensaje
    )
  }
  public consultar(){
    console.log(this.consulta)
    this.SReservacion.consulta(this.consulta).subscribe(
      (response) => {this.reservaciones=response,this.mensaje=null,console.log(response)},
      (err)=> this.mensaje=err.error.mensaje
    )
  }
  public limpiar(){
    window.location.reload();
  }
  public cambiarPagina(e:PageEvent){
    this.desde = e.pageIndex * e.pageSize;
    this.hasta = this.desde + this.pageSize;
  }
}
