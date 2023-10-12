import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecuperarUsuario } from 'src/app/model/recuperar-usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { UtilesService } from 'src/app/services/utiles.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-recuperar',
  templateUrl: './recuperar.component.html',
  styleUrls: ['./recuperar.component.css']
})
export class RecuperarComponent implements OnInit{
  
  usuario:RecuperarUsuario = new RecuperarUsuario()

  constructor(
    private SUtiles:UtilesService,
    private SUsuario:UsuarioService,
    private router:Router){}

  ngOnInit(): void {
    this.SUtiles.password();
  }

  enviar(){
    const email = this.usuario.email;
    if(email != null){
      this.SUsuario.mail(email).subscribe(
        response=>{
          Swal.fire(response.mensaje,"Ingresar secret key para recuperar cuenta","success")
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
    this.SUsuario.recuperar(this.usuario).subscribe(
      response=>{
        this.router.navigate(["/login"]);
        Swal.fire(response.mensaje,"Ingrese sesión con nueva contraseña","success");
      },
      err=>{
        Swal.fire("Error de validación",err.error.mensaje,"error");
      }
    )
  }

}
