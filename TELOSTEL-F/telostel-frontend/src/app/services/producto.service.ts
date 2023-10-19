import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../model/producto';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private host="http://localhost:8080/api/producto"

  constructor(private http:HttpClient) { }
  public listar():Observable<any>{
    return this.http.get(this.host)
  }
  public buscar(id:number):Observable<any>{
    return this.http.get(`${this.host}/${id}`)
  }
  public registar(bean:Producto):Observable<any>{
    return this.http.post(this.host,bean)
  }
  public actualizar(bean:Producto):Observable<any>{
    return this.http.put(this.host,bean)
  }
  public eliminar(id:number):Observable<any>{
    return this.http.delete(`${this.host}/${id}`)
  }
}
