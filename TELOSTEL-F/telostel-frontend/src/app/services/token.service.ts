import { Injectable } from '@angular/core';
import { Enlace } from '../model/enlace';

const TOKEN = 'AuthToken';
const NAME = 'AuthName';
const USERNAME = 'AuthUsername';
const ROL = 'AuthAuthorities';
const ENLACES = 'AuthEnlaces';
const USER_ID_KEY = 'UserId';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  enlaces: Enlace[] = []

  constructor() { }

  // ::::::::: TOKEN :::::::::
  public setToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN);
    window.sessionStorage.setItem(TOKEN, token);
  }
  public getToken(): string {
    return window.sessionStorage.getItem(TOKEN);
  }

  // ::::::::: NAME :::::::::
  public setNombre(nombre: string): void {
    window.sessionStorage.removeItem(NAME);
    window.sessionStorage.setItem(NAME, nombre);
  }
  public getNombre(): string {
    return window.sessionStorage.getItem(NAME);
  }

  // ::::::::: USERNAME :::::::::
  public setUsername(username: string): void {
    window.sessionStorage.removeItem(USERNAME);
    window.sessionStorage.setItem(USERNAME, username);
  }
  public getUsername(): string {
    return window.sessionStorage.getItem(USERNAME);
  }

  // ::::::::: ROL :::::::::
  public setRol(rol: string): void {
    window.sessionStorage.removeItem(ROL);
    window.sessionStorage.setItem(ROL, rol);
  }
  public getRol(): string {
    return window.sessionStorage.getItem(ROL);
  }

  // ::::::::: ENLACE :::::::::
  public setEnlaces(enlaces: string[]): void {
    window.sessionStorage.removeItem(ENLACES);
    window.sessionStorage.setItem(ENLACES, JSON.stringify(enlaces));
  }
  public getEnlaces(): Enlace[] {
    if (sessionStorage.getItem(ENLACES)) {
      JSON.parse(sessionStorage.getItem(ENLACES)).
        forEach(enlace => {
          this.enlaces.push(enlace)
        })
    }
    return this.enlaces;
  }

  // ::::::::: CERRAR SESIÓN :::::::::

  public logout(): void {
    window.sessionStorage.clear();
  }

  /*
  // ::::::::: USUARIO :::::::::
  public setIdUsuario(idUsuario: number): void {
    window.sessionStorage.removeItem(USER_ID_KEY);
    window.sessionStorage.setItem(USER_ID_KEY, idUsuario.toString());
  }

  public getIdUsuario(): number {
    const idUsuario = sessionStorage.getItem(USER_ID_KEY);
    return idUsuario ? parseInt(idUsuario, 10) : null;
  }
  */
 
}
