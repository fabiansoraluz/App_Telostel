import { Component, OnInit } from '@angular/core';
import { ConsultaReservacion } from 'src/app/model/consulta-reservacion';
import { Reservacion } from 'src/app/model/reservacion';
import { ReservacionService } from 'src/app/services/reservacion.service';

@Component({
  selector: 'app-consulta-reservacion',
  templateUrl: './consulta-reservacion.component.html',
  styleUrls: ['./consulta-reservacion.component.css']
})
export class ConsultaReservacionComponent implements OnInit{

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
}
