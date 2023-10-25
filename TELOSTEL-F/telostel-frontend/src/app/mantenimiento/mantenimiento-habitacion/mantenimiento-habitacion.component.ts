import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Habitacion } from 'src/app/model/habitacion';
import { TipoHabitacion } from 'src/app/model/tipohabitacion';
import { HabitacionService } from 'src/app/services/habitacion.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-mantenimiento-habitacion',
  templateUrl: './mantenimiento-habitacion.component.html',
  styleUrls: ['./mantenimiento-habitacion.component.css']
})
export class MantenimientoHabitacionComponent {

  habitacion:Habitacion = new Habitacion()
  listarHabitaciones:Habitacion[]

  constructor(
    private serHabitacion:HabitacionService,
    private router:Router){}
    tipos:TipoHabitacion[]
    ngOnInit():void{
      this.listaHabitaciones()
    }

    eliminarHabitacion(id: number) {
      Swal.fire({
        title: '¿Estás seguro de eliminar esta habitación?',
        text: 'Esta acción no se puede deshacer',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if (result.isConfirmed) {
          this.serHabitacion.deleteHabitacion(id).subscribe(
            response => {
              if (response) {
                Swal.fire('Habitación Eliminada', 'La habitación número ' + response.numero + ' fue eliminada', 'success');
                // Actualizar la lista de habitaciones después de la eliminación
                this.listaHabitaciones();
              } else {
                // Manejar el caso en el que la respuesta es nula
                Swal.fire('Error', 'No se pudo eliminar la habitación', 'error');
              }
            },
            err => {
              Swal.fire('Error al eliminar', err.error.mensaje, 'error');
            }
          );
        }
      });
    }
    
    
    listaHabitaciones(){
      this.serHabitacion.getHabitaciones().subscribe(response=>{
        this.listarHabitaciones=response
      })
    }

}
