import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Reservacion } from '../model/reservacion';

@Injectable({
  providedIn: 'root'
})
export class ReservacionService {

  private host="http://localhost:8080/api/reservacion"

  constructor(private http:HttpClient) { }

  public listar():Observable<any>{
    return this.http.get(this.host)
  }

  public buscar(id:number):Observable<any>{
    return this.http.get(this.host+"/"+id)
  }
  
  public registrar(bean:Reservacion):Observable<any>{
    return this.http.post(this.host,bean)
  }

  public servicios():Observable<any>{
    return this.http.get(this.host+"/servicio")
  }

  public buscarServicio(id:number):Observable<any>{
    return this.http.get(this.host+"/servicio/"+id)
  }
  
  public sedes():Observable<any>{
    return this.http.get(this.host+"/sede")
  }

  public reporte1(fecInicial:String, fecFinal:String):Observable<any>{
    return this.http.get(this.host+"/reporteFec"+"/"+fecInicial+"/"+fecFinal)
  }

  public reporteDetalle(fecInicial: Date, fecFinal: Date): Observable<Blob> {
    const url = `${this.host}/reporteFec2/${fecInicial}/${fecFinal}`;
  
    // Configuración para recibir la respuesta como array buffer
    const options = { responseType: 'arraybuffer' as 'json' };
  
    return this.http.get(url, options).pipe(
      map((pdfData: ArrayBuffer) => {
        // Retorna el Blob en lugar de la URL
        return this.createBlob(pdfData);
      })
    );
  }
  
  private createBlob(pdfData: ArrayBuffer): Blob {
    return new Blob([pdfData], { type: 'application/pdf' });
  }
  

}
