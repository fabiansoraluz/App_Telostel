import { Component, OnInit } from '@angular/core';
import { Habitacion } from 'src/app/model/habitacion';
import { HabitacionService } from 'src/app/services/habitacion.service';

@Component({
  selector: 'app-mantenimiento-habitacion',
  templateUrl: './mantenimiento-habitacion.component.html',
  styleUrls: ['./mantenimiento-habitacion.component.css']
})
export class MantenimientoHabitacionComponent implements OnInit{

  public habitaciones:[Habitacion]
  
  constructor(
    private SHabitacion:HabitacionService 
  ){}
  
  ngOnInit(): void {
    //Listar Habitaciones
    this.SHabitacion.listar().subscribe(
      response => {
        this.habitaciones=response
      }
    )
  }

}
