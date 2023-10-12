import { Injectable } from '@angular/core';
import { Enlace } from '../model/enlace';

const TOKEN = 'AuthToken';
const USERNAME = 'AuthUsername';
const ROL = 'AuthRol';
const ENLACES = 'AuthEnlaces';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  enlaces: Enlace[]=[]

  constructor() { }

  // ::::::::: TOKEN :::::::::
  public setToken(token:string):void{
    window.sessionStorage.removeItem(TOKEN);
    window.sessionStorage.setItem(TOKEN,token);
  }
  public getToken():string{
    return window.sessionStorage.getItem(TOKEN);
  }

  // ::::::::: USERNAME :::::::::
  public setUsername(username:string):void{
    window.sessionStorage.removeItem(USERNAME);
    window.sessionStorage.setItem(USERNAME,username);
  }
  public getUsername():string{
    return window.sessionStorage.getItem(USERNAME);
  }

  // ::::::::: ROL :::::::::
  public setRol(rol:string):void{
    window.sessionStorage.removeItem(ROL);
    window.sessionStorage.setItem(ROL,rol);
  }
  public getRol():string{
    return window.sessionStorage.getItem(ROL);
  }

  // ::::::::: ENLACE :::::::::
  public setEnlaces(enlaces:string[]):void{
    window.sessionStorage.removeItem(ENLACES);
    window.sessionStorage.setItem(ENLACES,JSON.stringify(enlaces));
  }
  public getEnlaces():Enlace[]{
    if(sessionStorage.getItem(ENLACES)){
      JSON.parse(sessionStorage.getItem(ENLACES)).
      forEach(enlace =>{
        this.enlaces.push(enlace)
      })
    }
    return this.enlaces;
  }

  // ::::::::: CERRAR SESIÓN :::::::::

  public logout():void{
    window.sessionStorage.clear();
  }

}
