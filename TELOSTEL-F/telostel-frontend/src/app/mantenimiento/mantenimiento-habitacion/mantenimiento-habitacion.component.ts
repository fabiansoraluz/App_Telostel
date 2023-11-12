import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Habitacion } from 'src/app/model/habitacion';
import { TipoHabitacion } from 'src/app/model/tipo-habitacion';
import { HabitacionService } from 'src/app/services/habitacion.service';


@Component({
  selector: 'app-mantenimiento-habitacion',
  templateUrl: './mantenimiento-habitacion.component.html',
  styleUrls: ['./mantenimiento-habitacion.component.css']
})
export class MantenimientoHabitacionComponent implements OnInit {

  public habitaciones: Habitacion[] = [];

  public tiposHabitacion: TipoHabitacion[] = [];

  selectedTipoId: number = 0;

  obj: any = {
    id: 0,
    numero: 0,
    piso: 0,
    createAt: "",
    estado: "",
    tipo: {
      id: 0,
      nombre: "",
      costo: 0
    }
  }

  constructor(
    private SHabitacion: HabitacionService
  ) { }

  ngOnInit(): void {
    // Listar Habitaciones
    this.SHabitacion.listar().subscribe((response) => {
      this.habitaciones = response;
    });

    // Listar Tipos de Habitación
    this.SHabitacion.listarTipos().subscribe((tipos) => {
      this.tiposHabitacion = tipos;
      console.log('Tipos de habitación:', this.tiposHabitacion);
    });
  }


  listarTipos() {
    this.SHabitacion.listarTipos().subscribe((tipos) => {
      this.tiposHabitacion = tipos;
      console.log('Tipos de habitación:', this.tiposHabitacion);
    });
  }
  
}
