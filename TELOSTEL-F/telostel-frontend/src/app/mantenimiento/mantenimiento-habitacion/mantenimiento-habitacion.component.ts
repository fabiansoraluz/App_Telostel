import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { Habitacion } from 'src/app/model/habitacion';
import { TipoHabitacion } from 'src/app/model/tipo-habitacion';
import { HabitacionService } from 'src/app/services/habitacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mantenimiento-habitacion',
  templateUrl: './mantenimiento-habitacion.component.html',
  styleUrls: ['./mantenimiento-habitacion.component.css']
})
export class MantenimientoHabitacionComponent implements OnInit {

  public habitaciones: Habitacion[] = [];
  public tiposHabitacion: TipoHabitacion[] = [];
  public formulario:FormGroup

  constructor(
    private SHabitacion: HabitacionService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    // Listar Habitaciones
    this.listarHabitaciones();

    // Listar Tipos de Habitación
    this.listarTipos();

    //Construimos el formulario
    this.formulario = this.formBuilder.group({
      piso:['',
        [
          Validators.required
        ]
      ],
      tipo:['',
        [
          Validators.required
        ]
      ]
    })
  }

  private listarHabitaciones(){
    this.SHabitacion.listar().subscribe((response) => {
      this.habitaciones = response;
    });
  }

  private listarTipos() {
    this.SHabitacion.listarTipos().subscribe((tipos) => {
      this.tiposHabitacion = tipos;
    });
  }
  
  public registrar(){
    
    var hab = new Habitacion();
    var tipo = new TipoHabitacion();

    let piso = this.formulario.get("piso").value
    let id_tipo = this.formulario.get("tipo").value
    tipo.id = parseInt(id_tipo)
  
    hab.piso=piso
    hab.tipo=tipo

    this.SHabitacion.registrar(hab).subscribe(
      (response) => {
        Swal.fire("Registro Exitoso","La habitación número "+response.numero+" ha sido registrado","success"),
        setTimeout(() => {
          window.location.reload();
        }, 3000); 
      },
      (err)=>Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }

  private esperarSegundos(segundos: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, segundos * 1000));
  }

}
