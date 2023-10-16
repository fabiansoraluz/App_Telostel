import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { errorTailorImports } from '@ngneat/error-tailor';
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
  
  public provincias:string[];
  public provincia:string="";
  public distritos:Ubigeo[];
  public id_ubigeo:number=0;
  public formulario:FormGroup;

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
    //Agregamos la lista de provincias
    this.SUbigeo.getProvincias().subscribe(
      response => this.provincias=response
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
        ],
        repeat:['',
          [
            Validators.required
          ]
        ]
      },{
        validators: this.passwordMatch
      })
  }
  //Método de validación
  passwordMatch(formGroup: FormGroup) {
    const password = formGroup.get('password').value;
    const repeat = formGroup.get('repeat').value;

    if (password === repeat) {
      return null; // Las contraseñas coinciden, no hay error
    } else {
      return { passwordMismatch: true }; // Las contraseñas no coinciden
    }
  }
  //Método para distritos
  listarDistritos():void{
    this.id_ubigeo=0;
    this.SUbigeo.getDistritos(this.provincia).subscribe(
      response => this.distritos=response
    )
  }
  //Método para registrar usuario
  registrarUsuario():void{
    //Validamos Ubigeo
    if(this.id_ubigeo==0){
      Swal.fire("Error de Ubigeo","Debes seleccionar un distrito","error")
      return
    }
    //Construimos Usuario
    var usuario = this.buildUser();
    //Registramos Usuario
    this.SUsuario.registrar(usuario).subscribe(
      response =>{
        this.router.navigate(["/login"])
        Swal.fire("Usuario registrado","El usuario " + response.empleado.nombre+" ha sido registrado","success")
      },
      err=>{
        Swal.fire("Error al registrar",err.error.mensaje,"error")
      }
    )
  }
  buildUser():Usuario{
    
    //Instancimao objeto Ubigeo
    var ubigeo    = new Ubigeo();
    ubigeo.id     = this.id_ubigeo

    //Instanciamos objeto Empleado
    var empleado      = new Empleado();
    empleado.nombre   = this.formulario.get('nombre').value
    empleado.apellido = this.formulario.get('apellido').value
    empleado.dni      = this.formulario.get('dni').value
    empleado.celular  = this.formulario.get('celular').value
    empleado.ubigeo   = ubigeo

    //Instanciamos objeto Usuario
    var usuario = new Usuario();
    usuario.username  = this.formulario.get('username').value
    usuario.password  = this.formulario.get('password').value
    usuario.correo    = this.formulario.get('correo').value
    usuario.empleado = empleado
    return usuario
  }
}
