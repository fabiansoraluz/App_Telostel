import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private host = "http://localhost:8080/api/empleado"

  constructor(private http:HttpClient) {}

  public buscarXUsername(username:String):Observable<any>{
    return this.http.get(this.host+"/username/"+username)
  }
  public listarCargos():Observable<any>{
    return this.http.get(this.host+"/cargo")
  }
}
