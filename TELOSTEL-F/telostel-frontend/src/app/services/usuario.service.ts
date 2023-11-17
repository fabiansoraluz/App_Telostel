import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUsuario } from '../model/login-usuario';
import { JwtDto } from '../model/jwt-dto';
import { Usuario } from '../model/usuario';
import { RecuperarUsuario } from '../model/recuperar-usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private host = "http://localhost:8080/api/usuario";
  private headers = new HttpHeaders({'Content-Type':'application/json'})

  constructor(private http:HttpClient) { }

  public login(usuario:LoginUsuario):Observable<any>{
    return this.http.post<JwtDto>(this.host+"/login",usuario,{headers: this.headers});
  }
  public buscar(username:String):Observable<any>{
    return this.http.get<Usuario>(this.host+"/"+username);
  }
  public registrar(usuario:Usuario):Observable<any>{
    return this.http.post<Usuario>(this.host,usuario,{headers:this.headers});
  }
  public actualizar(usuario:Usuario):Observable<any>{
    return this.http.put<Usuario>(this.host,usuario,{headers:this.headers});
  }
  public mail(correo:string):Observable<any>{
    return this.http.post<string>(this.host+"/mail/"+correo,null,{headers:this.headers})
  }
  public recuperar(usuario:RecuperarUsuario):Observable<any>{
    return this.http.post<string>(this.host+"/recuperar",usuario,{headers:this.headers})
  }
}
