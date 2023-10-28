import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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
}
