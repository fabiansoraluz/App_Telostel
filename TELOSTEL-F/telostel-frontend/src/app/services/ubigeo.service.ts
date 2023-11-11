import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ubigeo } from '../model/ubigeo';
import { Distrito } from '../model/distrito';

@Injectable({
  providedIn: 'root'
})
export class UbigeoService {

  private header = new HttpHeaders({'Content-Type':'application/json'})
  private host = "http://localhost:8080/api/ubigeo/"

  constructor(private http:HttpClient) { }

  public departamentos():Observable<any>{
    return this.http.get<string[]>(this.host+"departamento")
  }
  public provincias(departamento:string):Observable<any>{
    return this.http.get<string[]>(this.host+"provincia/"+departamento)
  }
  public distrito(provincia:string):Observable<any>{
    return this.http.get<Distrito[]>(this.host+"distrito/"+provincia)
  }

}
