import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUsuario } from '../model/login-usuario';
import { JwtDto } from '../model/jwt-dto';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private host = "http://localhost:8080/api/usuario/";
  private headers = new HttpHeaders({'Content-Type':'application/json'})

  constructor(private http:HttpClient) { }

  public login(usuario:LoginUsuario):Observable<any>{
    return this.http.post<JwtDto>(this.host+"login",usuario,{headers: this.headers});
  }
}
