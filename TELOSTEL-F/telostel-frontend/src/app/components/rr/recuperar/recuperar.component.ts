import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RecuperarUsuario } from 'src/app/model/recuperar-usuario';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { UtilesService } from 'src/app/services/utiles.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-recuperar',
  templateUrl: './recuperar.component.html',
  styleUrls: ['./recuperar.component.css']
})
export class RecuperarComponent implements OnInit{
  
  public formulario:FormGroup;

  constructor(
    private SUtiles:UtilesService,
    private SUsuario:UsuarioService,
    private router:Router,
    private formBuilder:FormBuilder){}

  ngOnInit(): void {
    //Agregamos evento del password
    this.SUtiles.password();
    //Costruimos el formulario para validar
    this.formulario = this.formBuilder.group({
      correo:['',
        [
          Validators.required,
          Validators.email
        ]
      ],
      key:['',
        [
          Validators.required,
          Validators.pattern("[A-Z\\d]{4}[-][A-Z\\d]{4}[-][A-Z\\d]{4}")
        ]
      ],
      password:['',
        [
          Validators.required,
          Validators.pattern("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,12}")
        ]
      ]
    })
  }

  enviar(){
    const email = this.formulario.get('correo').value;
    if(email != null){
      this.SUsuario.mail(email).subscribe(
        response=>{
          Swal.fire(response.mensaje,"Ingresar SECRET KEY para recuperar cuenta","success")
        },
        err=>{
          Swal.fire("Error de envio",err.error.mensaje,"error");
        }
      )
    }else{
      Swal.fire("Error de Email","Debes ingresar un correo","error");
    }
  }
  recuperar(){
    var usuario = this.buildUser();
    this.SUsuario.recuperar(usuario).subscribe(
      response=>{
        this.router.navigate(["/login"]);
        Swal.fire(response.mensaje,"Ingrese sesión con nueva contraseña","success");
      },
      err=>{
        Swal.fire("Error de validación",err.error.mensaje,"error");
      }
    )
  }
  buildUser():RecuperarUsuario{
    var usuario = new RecuperarUsuario();
    usuario.email=this.formulario.get('correo').value
    usuario.key=this.formulario.get('key').value
    usuario.password=this.formulario.get('password').value
    return usuario;
  }
}
