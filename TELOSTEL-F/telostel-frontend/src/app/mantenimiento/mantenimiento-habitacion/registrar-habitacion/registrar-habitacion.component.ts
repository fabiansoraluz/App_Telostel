import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Habitacion } from 'src/app/model/habitacion';
import { TipoHabitacion } from 'src/app/model/tipohabitacion';
import { HabitacionService } from 'src/app/services/habitacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar-habitacion',
  templateUrl: './registrar-habitacion.component.html',
  styleUrls: ['./registrar-habitacion.component.css']
})
export class RegistrarHabitacionComponent {
  habitacion:Habitacion = new Habitacion()
  listarHabitaciones:Habitacion[]
  tipos:TipoHabitacion[]

  constructor(
    private serHabitacion:HabitacionService,
    private router:Router){}

  ngOnInit():void{

    this.serHabitacion.getHabitaciones().subscribe(response=>{
      this.listarHabitaciones=response
    })

    this.serHabitacion.getTipoHabitacion().subscribe(response=>{
      this.tipos=response
    })
  }

  registrarHabitacion():void{
    this.serHabitacion.saveHabitaciones(this.habitacion).subscribe(
      response => {
        this.router.navigate(["/habitacion"])
        Swal.fire("Habitacion Registrada","La habitacion numero " +response.numero)
      },     
      err=>{
        Swal.fire("Error al registrar",err.error.mensaje,"error")
      }
  )
      console.log(this.habitacion)
  }
}
