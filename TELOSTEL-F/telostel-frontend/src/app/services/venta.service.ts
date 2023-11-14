import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Venta } from '../model/venta';
import { Observable, map, switchMap } from 'rxjs';
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

  public listaVenta():Observable<any>{
    return this.http.get(this.host)
  }

  public generarReportePorFecha(fecha: String): Observable<Blob> {
    const url = `${this.host}/fecha/${fecha}`;

    const options = { responseType: 'arraybuffer' as 'json' };

    return this.http.get(url, options).pipe(
      map((pdfData: ArrayBuffer) => {
        return new Blob([pdfData], { type: 'application/pdf' });
      })
    );
  }

  public consultaVenta(fecha:String):Observable<any>{
    return this.http.get(this.host+"/consulta"+"/"+fecha)
  }

}
