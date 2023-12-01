import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Reservacion } from 'src/app/model/reservacion';
import { Servicio } from 'src/app/model/servicio';
import { ReservacionService } from 'src/app/services/reservacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-detalle-reservacion',
  templateUrl: './detalle-reservacion.component.html',
  styleUrls: ['./detalle-reservacion.component.css']
})
export class DetalleReservacionComponent implements OnInit{
  
  public reserva:Reservacion=new Reservacion();
  public costo_servicios=0;

  constructor(private route: ActivatedRoute,private SReserva:ReservacionService){
  }
  ngOnInit(): void {
    const id_prestamo = this.route.snapshot.params['idReserva'];
    this.SReserva.buscar(id_prestamo).subscribe(
      (response)=>{
        this.reserva=response,
        this.getCostoServicio(this.reserva.servicios)
      },
      (err)=>Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }
  getCostoServicio(lista:Servicio[]){
    var suma=0
    lista.forEach((x:Servicio)=>{
      suma+=x.costo
    })
    this.costo_servicios=suma;
  }
}
