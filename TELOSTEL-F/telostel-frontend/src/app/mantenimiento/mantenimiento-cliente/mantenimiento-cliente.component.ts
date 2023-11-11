import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Cliente } from 'src/app/model/cliente';
import { Distrito } from 'src/app/model/distrito';
import { ClienteService } from 'src/app/services/cliente.service';
import { UbigeoService } from 'src/app/services/ubigeo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mantenimiento-cliente',
  templateUrl: './mantenimiento-cliente.component.html',
  styleUrls: ['./mantenimiento-cliente.component.css']
})
export class MantenimientoClienteComponent implements OnInit{
  
  formulario:FormGroup
  clientes:Cliente[]=[]
  cliente:Cliente = new Cliente()
  isUpdate:boolean

  //Configuración de ubigeo
  departamentos:string[]
  departamento:string=""
  provincias:string[]
  provincia:string=""
  distritos:Distrito[]
  idUbigeo:string=""

  //Mensaje: No hay clientes registrado
  error_lista:String

  constructor(
    private SCliente:ClienteService,
    private SUbigeo:UbigeoService,
    private formBuilder:FormBuilder
  ){}

  ngOnInit(): void {

    //Listamos los clientes
    this.listar()
    //Listar departamentos
    this.SUbigeo.departamentos().subscribe(
      response => this.departamentos=response
    )
    //Construimos al cliente
    this.formulario = this.formBuilder.group({
      id:[0],
      nombre:['',[
        Validators.required,
        Validators.pattern("[a-zA-ZáéíóúÁÉÍÓÚñ\\s]{4,30}")
      ]],
      apellido:['',[
        Validators.required,
        Validators.pattern("[a-zA-ZáéíóúÁÉÍÓÚñ\\s]{4,30}")
      ]],
      celular:['',[
        Validators.required,
        Validators.pattern("[\\d]{7,9}")
      ]],
      dni:['',[
        Validators.required,
        Validators.pattern("[\\d]{8}")
      ]]
    })
  }

  public listar(){
    this.SCliente.listar().subscribe(
      (response) => this.clientes=response,
      (err)=> this.error_lista=err.error.mensaje
    )
  }

  public listarProvincias(){
    this.provincia=""
    this.cliente.ubigeo.id=""
    this.SUbigeo.provincias(this.departamento).subscribe(
      response => this.provincias=response
    )
  }

  public listarDistritos(){
    this.cliente.ubigeo.id=""
    this.SUbigeo.distrito(this.provincia).subscribe(
      response => this.distritos = response
    )
  }

  public llenarDatos(idCliente:number){
    this.SCliente.buscar(idCliente).subscribe(
      (response)=>{
        //Llenamos el formulario
        this.formulario.patchValue({
          id:response.id,
          nombre:response.nombre,
          apellido:response.apellido,
          celular:response.celular,
          dni:response.dni
        })
        //Llenamos el ubigeo
        this.departamento = response.ubigeo.departamento
        this.listarProvincias()
        this.provincia = response.ubigeo.provincia
        this.listarDistritos()
        this.cliente.ubigeo.id = response.ubigeo.id
        //Llenamos al cliente
        this.cliente = response
        //Declaramos que es actualizar
        this.isUpdate=true
      },
      (err)=>Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }
  public eliminar(idCliente:number){
    Swal.fire({
      title: 'Eliminar Cliente',
      text: "¿Seguro que deseas eliminar al cliente?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) { 
        this.SCliente.eliminar(idCliente).subscribe(
          (response)=>{
            Swal.fire("Cliente Eliminada",response.mensaje,"success")
            this.listar();
          },
          (err) => {
            Swal.fire("Error del Sistema",err.error.mensaje,"error")
          }
    
        )
      }
    })
  }
  public registrar(){

    //Validamos el ubigeo
    if(this.cliente.ubigeo.id === ""){
      Swal.fire("Error de Ubigeo","Debes seleccionar un ubigeo","error")
      return
    }
    this.buildCliente();
    this.SCliente.registrar(this.cliente).subscribe(
      (response) => {
        Swal.fire("Cliente Registrado",`El cliente ${response.nombre} ha sido registrado`,"success")
        setTimeout(() => {
          window.location.reload();
        }, 3000); 
      },
      (err)=>Swal.fire("Error de Sistema",err.error.mensaje,"error")
    )
  }
  public actualizar(){
    //Validamos el ubigeo
    if(this.cliente.ubigeo.id === ""){
      Swal.fire("Error de Ubigeo","Debes seleccionar un ubigeo","error")
      return
    }
    this.buildCliente();
    this.SCliente.actualizar(this.cliente).subscribe(
      (response) => {
        Swal.fire("Cliente Actualizado",`El cliente ${response.nombre} ha sido actualizado`,"success")
        setTimeout(() => {
          window.location.reload();
        }, 3000); 
      },
      (err)=>Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }
  private buildCliente(){
    this.cliente.id = this.formulario.get("id").value
    this.cliente.nombre = this.formulario.get("nombre").value
    this.cliente.apellido = this.formulario.get("apellido").value
    this.cliente.celular = this.formulario.get("celular").value
    this.cliente.dni = this.formulario.get("dni").value
  }

}
