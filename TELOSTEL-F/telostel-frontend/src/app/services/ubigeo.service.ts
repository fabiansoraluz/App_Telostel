import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ubigeo } from '../model/ubigeo';

@Injectable({
  providedIn: 'root'
})
export class UbigeoService {

  private header = new HttpHeaders({'Content-Type':'application/json'})
  private host = "http://localhost:8080/api/ubigeo"

  constructor(private http:HttpClient) { }

  public getProvincias():Observable<any>{
    return this.http.get<string[]>(this.host+"/provincias",{headers:this.header})
  }

  public getDistritos(provincia:string):Observable<any>{
    return this.http.get<Ubigeo[]>(this.host+"/distritos/"+provincia,{headers:this.header})
  }

}
