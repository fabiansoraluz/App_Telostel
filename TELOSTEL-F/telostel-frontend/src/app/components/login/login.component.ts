import { Component,OnInit } from '@angular/core';
import { UtilesService } from '../../services/utiles.service';
import { LoginUsuario } from '../../model/login-usuario';
import { UsuarioService } from '../../services/usuario.service';
import { TokenService } from '../../services/token.service';
import { Router} from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  usuario:LoginUsuario=new LoginUsuario();

  constructor(
    private SUtiles:UtilesService,
    private SUsuario:UsuarioService,
    private SToken:TokenService,
    private router:Router){}

  ngOnInit(): void {

    //Implementamos efectos del login
    this.SUtiles.login();
    //Implementamos función de password
    this.SUtiles.password();
  }

  public login(){
    this.SUsuario.login(this.usuario).subscribe(
      response => {
        this.SToken.setToken(response.token);
        this.SToken.setNombre(response.nombre);
        this.SToken.setUsername(response.username);
        this.SToken.setRol(response.rol);
        this.SToken.setEnlaces(response.enlaces);
        this.router.navigate(["/system/dashboard"])
      },
      err =>{
        Swal.fire("Se produjo un error",err.error.mensaje,"error")
      }
    )
  }

  public sistema() {
    // Implementación del método login
    // ...

    // Redirigir al componente AsideComponent
    this.router.navigate(['/aside']);  // Reemplazar con la ruta correcta
  }

}
