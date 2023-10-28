import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Venta } from '../model/venta';
import { Observable, switchMap } from 'rxjs';
import { Carrito } from '../model/carrito';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private host="http://localhost:8080/api/venta"

  constructor(private http:HttpClient) { }

  public registrar(venta: Venta, carrito: Carrito[]){
    this.http.post<Venta>(this.host, venta).pipe(
      switchMap((response) => {
        let id = response.id;
        return this.http.post(this.host + "/detalle/" + id, carrito);
      })
    ).subscribe(
      (detalleRespuesta) => {
        Swal.fire("Venta Registrada","La venta se registro con exito","success")
    },(err)=>{
      Swal.fire("Error de registro",err.error.mensaje,"error")
    });
  }

}
