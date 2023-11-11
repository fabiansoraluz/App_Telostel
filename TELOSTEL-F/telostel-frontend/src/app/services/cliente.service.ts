import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../model/cliente';
import { ConsultaCliente } from '../model/consulta-cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private host = "http://localhost:8080/api/cliente"

  constructor(private http:HttpClient) { }
  
  public listar():Observable<any>{
    return this.http.get(this.host)
  }
  public buscar(id:number):Observable<any>{
    return this.http.get(this.host+"/"+id)
  }
  public buscarXNombre(nombre:String):Observable<any>{
    return this.http.get(this.host+"/nombre/"+nombre)
  }
  public registrar(bean:Cliente):Observable<any>{
    return this.http.post(this.host,bean);
  }
  public consulta(bean:ConsultaCliente):Observable<any>{
    return this.http.post<Cliente[]>(this.host+"/consultar",bean)
  }
  public actualizar(bean:Cliente):Observable<any>{
    return this.http.put(this.host,bean);
  }
  public eliminar(idCliente:number):Observable<any>{
    return this.http.delete(this.host+"/"+idCliente)
  }
}
