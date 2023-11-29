import { Component,OnInit } from '@angular/core';
import { UtilesService } from '../../services/utiles.service';
import { LoginUsuario } from '../../model/login-usuario';
import { UsuarioService } from '../../services/usuario.service';
import { TokenService } from '../../services/token.service';
import { Router} from '@angular/router';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  formulario:FormGroup;

  constructor(
    private SUtiles:UtilesService,
    private SUsuario:UsuarioService,
    private SToken:TokenService,
    private router:Router,
    private formBuilder:FormBuilder){}

  ngOnInit(): void {

    //Implementamos efectos del login
    this.SUtiles.login();
    //Implementamos función de password
    this.SUtiles.password();
    //Construimos un formulario
    this.formulario = this.formBuilder.group({
      username:['',
        [
          Validators.required
        ]
      ],
      password:['',
        [
          Validators.required
        ]
      ]
    })
  }

  public login(){
    var usuario = this.buildLoginUsuario();
    this.SUsuario.login(usuario).subscribe(
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
  buildLoginUsuario():LoginUsuario{
    var usuario = new LoginUsuario();
    usuario.username=this.formulario.get('username').value
    usuario.password=this.formulario.get('password').value
    return usuario;
  }

}
