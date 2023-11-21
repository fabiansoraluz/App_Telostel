import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { errorTailorImports } from '@ngneat/error-tailor';
import { Distrito } from 'src/app/model/distrito';
import { Empleado } from 'src/app/model/empleado';
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
  
  public formulario:FormGroup;
  public usuario = new Usuario();

  //Configuración de ubigeo
  departamentos:string[]
  departamento:string=""
  provincias:string[]
  provincia:string=""
  distritos:Distrito[]
  idUbigeo:string=""

  constructor(
    private SUtiles:UtilesService,
    private SUbigeo:UbigeoService,
    private SUsuario:UsuarioService,
    private router:Router,
    private formBuilder:FormBuilder
  ){}

  ngOnInit(): void {
    //Agregamos evento del password
    this.SUtiles.password();
    //Listar departamentos
    this.SUbigeo.departamentos().subscribe(
      response => this.departamentos=response
    )
    //Construimos el formulario para validar
    this.formulario = this.formBuilder.group({
      nombre: ['',
        [
          Validators.required,
          Validators.pattern("[a-zA-ZáéíóúÁÉÍÓÚñ\\s]{4,30}")
        ]
      ],
      apellido:['',
        [
          Validators.required,
          Validators.pattern("[a-zA-ZáéíóúÁÉÍÓÚñ\\s]{4,30}")
        ]
      ],
      dni:['',
        [
          Validators.required,
          Validators.pattern("[\\d]{8}")
        ]
      ],
      celular:['',
        [
          Validators.required,
          Validators.pattern("[\\d]{7,9}")
        ]
      ],
      correo:['',
        [
          Validators.required,
          Validators.email
        ]
      ],
      distrito:[0],
      username:['',
        [
          Validators.required,
          Validators.pattern("[a-zA-ZáéíóúÁÉÍÓÚñ.\\d]{6,20}")
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
  //Método para registrar usuario
  registrarUsuario():void{
    //Construimos Usuario
    this.buildUser();
    //Validamos Ubigeo
    if(this.usuario.empleado.ubigeo.id === ''){
      Swal.fire("Error del Sistema","Obligatorio seleccionar ubigeo","error")
      return;
    }
    //Registramos Usuario
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

  public listarProvincias(){
    this.provincia=""
    this.usuario.empleado.ubigeo.id=""
    this.SUbigeo.provincias(this.departamento).subscribe(
      response => this.provincias=response
    )
  }

  public listarDistritos(){
    this.usuario.empleado.ubigeo.id=""
    this.SUbigeo.distrito(this.provincia).subscribe(
      response => this.distritos = response
    )
  }

  buildUser(){

    //Instanciamos objeto Empleado
    this.usuario.empleado.nombre   = this.formulario.get('nombre').value
    this.usuario.empleado.apellido = this.formulario.get('apellido').value
    this.usuario.empleado.dni      = this.formulario.get('dni').value
    this.usuario.empleado.celular  = this.formulario.get('celular').value
    this.usuario.empleado.cargo.id = 1; //Cargo administrador por defecto

    //Instanciamos objeto Usuario
    this.usuario.username  = this.formulario.get('username').value
    this.usuario.password  = this.formulario.get('password').value
    this.usuario.correo    = this.formulario.get('correo').value
  }
}
