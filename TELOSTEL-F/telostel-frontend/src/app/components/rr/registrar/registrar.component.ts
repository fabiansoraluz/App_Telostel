import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ubigeo } from 'src/app/model/ubigeo';
import { Usuario } from 'src/app/model/usuario';
import { UbigeoService } from 'src/app/services/ubigeo.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { UtilesService } from 'src/app/services/utiles.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit{
  
  usuario:Usuario = new Usuario()
  provincias:string[];
  provincia:string="";
  distritos:Ubigeo[];

  constructor(
    private SUtiles:UtilesService,
    private SUbigeo:UbigeoService,
    private SUsuario:UsuarioService,
    private router:Router){}

  ngOnInit(): void {
    //Agregamos evento del password
    this.SUtiles.password();
    //Agregamos la lista de provincias
    this.SUbigeo.getProvincias().subscribe(
      response => this.provincias=response
    )
  }
  //Método para distritos
  listarDistritos():void{
    this.usuario.empleado.ubigeo.id=0
    this.SUbigeo.getDistritos(this.provincia).subscribe(
      response => this.distritos=response
    )
  }
  //Método para registrar usuario
  registrarUsuario():void{
    this.SUsuario.registrar(this.usuario).subscribe(
      response =>{
        this.router.navigate(["/login"])
        Swal.fire("Usuario registrado","El usuario " + response.empleado.nombre+" ha sido registrado","success")
      },
      err=>{
        Swal.fire("Error al registrar",err.error.mensaje,"error")
      }
    )
  }
}
