import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Habitacion } from '../model/habitacion';

@Injectable({
  providedIn: 'root'
})
export class HabitacionService {

  private host="http://localhost:8080/api/habitacion"

  constructor(private http:HttpClient) { }

  //CRUD

  public listar():Observable<any>{
    return this.http.get(this.host)
  }

  public buscar(id:number):Observable<any>{
    return this.http.get(this.host+"/"+id)
  }

  public registrar(bean:Habitacion):Observable<any>{
    return this.http.post(this.host,bean)
  }

  public actualizar(bean:Habitacion):Observable<any>{
    return this.http.put(this.host,bean)
  }

  public eliminar(id:number):Observable<any>{
    return this.http.delete(this.host+"/"+id)
  }

  //CONSULTAS PERSONALIZADAS
  public ultimoPiso():Observable<any>{
    return this.http.get(this.host+"/ultimoPiso")
  }
  public buscarXPiso(piso:number):Observable<any>{
    return this.http.get(this.host+"/piso/"+piso)
  }
  public buscarXTipo(id_tipo:number):Observable<any>{
    return this.http.get(this.host+"/tipo/"+id_tipo)
  }
}
