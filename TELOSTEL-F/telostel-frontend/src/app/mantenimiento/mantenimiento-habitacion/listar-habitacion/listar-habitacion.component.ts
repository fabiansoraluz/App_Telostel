import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Habitacion } from 'src/app/model/habitacion';
import { HabitacionService } from 'src/app/services/habitacion.service';

@Component({
  selector: 'app-listar-habitacion',
  templateUrl: './listar-habitacion.component.html',
  styleUrls: ['./listar-habitacion.component.css']
})
export class ListarHabitacionComponent {

  habitacion:Habitacion = new Habitacion()
  listarHabitaciones:Habitacion[]

  constructor(
    private serHabitacion:HabitacionService){}

    ngOnInit():void{

      this.serHabitacion.getHabitaciones().subscribe(response=>{
        this.listarHabitaciones=response
      })
    }
}
