import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Habitacion } from '../model/habitacion';

@Injectable({
  providedIn: 'root'
})
export class HabitacionService {
  private header = new HttpHeaders({'Content-Type':'application/json'})
  private host = "http://localhost:8080/api/habitacion"

  constructor(private http:HttpClient) { }

  public getTipoHabitacion():Observable<any>{
    return this.http.get<String[]>(this.host+"/tipos",{headers:this.header})
  }

  public getHabitaciones():Observable<any>{
    return this.http.get<String[]>(this.host+"/listar",{headers:this.header})
  }

  public saveHabitaciones(bean:Habitacion):Observable<any>{
    return this.http.post<Habitacion>(this.host+"/registrar",bean)
  }

  public deleteHabitacion(cod:number):Observable<any>{
    return this.http.delete(this.host+"/eliminar/"+cod)
  }
}
