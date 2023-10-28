import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
}
