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
  public isUpdate = false
  private habitacion:Habitacion

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
      ],
      estado:['',
        [
          Validators.required
        ]
      ]
    })

    //Si es registrar settear estado en disponible y desabilitamos para no permitir modificaciones
    if(!this.isUpdate){
      this.formulario.patchValue({
        estado:"disponible"
      })
      this.formulario.get("estado").disable();
    }
    
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
    
    this.habitacion = new Habitacion();
    var tipo = new TipoHabitacion();

    let piso = this.formulario.get("piso").value
    let id_tipo = this.formulario.get("tipo").value
    tipo.id = parseInt(id_tipo)
  
    this.habitacion.piso=piso
    this.habitacion.tipo=tipo

    this.SHabitacion.registrar(this.habitacion).subscribe(
      (response) => {
        Swal.fire("Registro Exitoso","La habitación número "+response.numero+" ha sido registrado","success"),
        setTimeout(() => {
          window.location.reload();
        }, 3000); 
      },
      (err)=>Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }

  public actualizar(){
    this.habitacion.tipo.id = this.formulario.get("tipo").value
    this.habitacion.estado = this.formulario.get("estado").value

    this.SHabitacion.actualizar(this.habitacion).subscribe(
      (response) => { 
        Swal.fire("Actualización Exitosa","La habitación número "+response.numero+" ha sido actualizada","success"),
        setTimeout(() => {
          window.location.reload();
        }, 3000); 
      },
      (err)=>Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }

  public seleccionarHab(bean:Habitacion){
    this.isUpdate=true
    this.habitacion=bean
    this.formulario.patchValue({
      piso: bean.piso,
      tipo: bean.tipo.id,
      estado: bean.estado
    })
    this.formulario.get("piso").disable()
    this.formulario.get("estado").enable()
  }
}
