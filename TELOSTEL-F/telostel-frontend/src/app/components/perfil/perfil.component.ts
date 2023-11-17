import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CargoEmpleado } from 'src/app/model/cargo-empleado';
import { Distrito } from 'src/app/model/distrito';
import { Usuario } from 'src/app/model/usuario';
import { EmpleadoService } from 'src/app/services/empleado.service';
import { TokenService } from 'src/app/services/token.service';
import { UbigeoService } from 'src/app/services/ubigeo.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit{
  
  public formulario:FormGroup
  public usuario = new Usuario()
  public cargos:CargoEmpleado[] = []
  
  //Configuración de ubigeo
  departamentos:string[]
  departamento:string=""
  provincias:string[]
  provincia:string=""
  distritos:Distrito[]
  idUbigeo:string=""

  constructor(
    private FB:FormBuilder,
    private SToken:TokenService,
    private SEmpleado:EmpleadoService,
    private SUsuario:UsuarioService,
    private SUbigeo:UbigeoService
  ){}

  ngOnInit(): void {

    // Listar Cargos
    this.listarCargos()

    //Listar departamentos
    this.SUbigeo.departamentos().subscribe(
      response => this.departamentos=response
    )

    this.formulario = this.FB.group({
      nombre:['',
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
      ubigeo:['',
        [Validators.required]
      ],
      cargo:['',
        [Validators.required]
      ],
      correo:['',
        [
          Validators.required,
          Validators.email
        ]
      ]
    })
    let username = this.SToken.getUsername()
    //Buscar Usuario
    this.SUsuario.buscar(username).subscribe(
      (response) => {
        this.rellenarValores(response),
        this.usuario=response
      },
      (err) => Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }
  listarCargos(){
    this.SEmpleado.listarCargos().subscribe(
      (response) => this.cargos = response,
      (err) => Swal.fire("Error del sistema",err.error.mensaje,"error")
    )
  }
  rellenarValores(bean:Usuario){
    this.formulario.patchValue({
      nombre: bean.empleado.nombre,
      apellido: bean.empleado.apellido,
      dni: bean.empleado.dni,
      celular: bean.empleado.celular,
      cargo: bean.empleado.cargo.id,
      correo: bean.correo,
      ubigeo: bean.empleado.ubigeo.id
    })
    //Llenamos el ubigeo
    this.departamento = bean.empleado.ubigeo.departamento
    this.listarProvincias()
    this.provincia = bean.empleado.ubigeo.provincia
    this.listarDistritos()
    this.usuario.empleado.ubigeo.id = bean.empleado.ubigeo.id
  }
  actualizar(){

    //Construimos Usuario
    this.buildUser();

    this.SUsuario.actualizar(this.usuario).subscribe(
      (response) => Swal.fire("Usuario Actualizado","El usuario " + response.empleado.nombre + " ha sido actualizado","success"),
      (err) => Swal.fire("Error del Sistema","Error al actualizar el usuario","error")
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

  public seleccionarDistrito(){
    this.formulario.patchValue({
      ubigeo:this.usuario.empleado.ubigeo.id
    })
  }

  private buildUser(){
    console.log(this.formulario.get("ubigeo").value)
    this.usuario.empleado.nombre    = this.formulario.get("nombre").value
    this.usuario.empleado.apellido  = this.formulario.get("apellido").value
    this.usuario.empleado.dni       = this.formulario.get("dni").value
    this.usuario.empleado.celular   = this.formulario.get("celular").value
    this.usuario.empleado.cargo.id  = this.formulario.get("cargo").value
    this.usuario.correo             = this.formulario.get("correo").value
  }
}
