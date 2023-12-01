import { Component, OnInit } from '@angular/core';
import { Habitacion } from 'src/app/model/habitacion';
import { HabitacionService } from 'src/app/services/habitacion.service';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { Reservacion } from 'src/app/model/reservacion';
import { Sede } from 'src/app/model/sede';
import { Servicio } from 'src/app/model/servicio';
import { ReservacionService } from 'src/app/services/reservacion.service';
import { group } from '@angular/animations';
import { Cliente } from 'src/app/model/cliente';
import { ClienteService } from 'src/app/services/cliente.service';
import * as moment from 'moment';
import { EmpleadoService } from 'src/app/services/empleado.service';
import { Empleado } from 'src/app/model/empleado';
import { TokenService } from 'src/app/services/token.service';
import { TipoHabitacion } from 'src/app/model/tipo-habitacion';
import { HabitacionDTO } from 'src/app/model/habitacion-dto';

@Component({
  selector: 'app-servicio-reservacion',
  templateUrl: './servicio-reservacion.component.html',
  styleUrls: ['./servicio-reservacion.component.css']
})
export class ServicioReservacionComponent implements OnInit{
  
  //Pisos y Habitaciones
  public habitaciones:[Habitacion]
  public sedes:[Sede]
  public sedeSeleccionada:number=0
  public tipos:TipoHabitacion[]=[]
  public tipoSeleccionado:string=""
  public mensaje:string=''

  //Reservacion
  public reservacion= new Reservacion()
  public servicios:[Servicio]
  public formulario:FormGroup
  public habitacion:Habitacion
  public cliente:Cliente
  public empleado:Empleado

  //Buscar Cliente
  public clientes:[Cliente]
  public nombre:string

  //Importe
  public impReserva:number=0.00
  public impServicio:number=0.00
  public impTotal:number=0.00

  //Servicios del cliente
  public servicios_list:Servicio[] = []

  constructor(
    private SHabitacion:HabitacionService,
    private SReservacion:ReservacionService,
    private SEmpleado:EmpleadoService,
    private SCliente:ClienteService,
    private SToken:TokenService,
    private formBuilder:FormBuilder
  ){}

  ngOnInit(): void {

    //Buscar username
    let username = this.SToken.getUsername();

    //Buscar empleado
    this.SEmpleado.buscarXUsername(username).subscribe(
      response => this.empleado=response
    )

    //Listar Sedes
    this.SReservacion.sedes().subscribe(
      response =>{
        this.sedes = response
      }
    )

    //Listar Tipos
    this.SHabitacion.listarTipos().subscribe(
      response=>{
        this.tipos = response
      }
    )

    //Listar Servicios
    this.listarServicios();
    
    //Listar Clientes
    this.SCliente.listar().subscribe(
      response =>{
        this.clientes=response
      }
    )

    //Construir formulario
    this.formulario = this.formBuilder.group({
      cliente:['',
      [
        Validators.required
      ]],
      checkIn:['',
      [
        Validators.required,
        this.checkInValidator.bind(this)
      ]],
      checkOut:['',
      [
        Validators.required,
        this.checkOutValidator.bind(this)
      ]]
    })
  }

  listarServicios(){
    this.SReservacion.servicios().subscribe(
      response =>{
        this.servicios = response
      }
    )
  }

  //Validacion de CheckIn
  checkInValidator(control:any){
    var checkIn = new Date(control.value);
    checkIn.setDate(checkIn.getDate() + 1);
    checkIn.setHours(0, 0, 0, 0);

    const actual = new Date();
    actual.setHours(0, 0, 0, 0);

    if(checkIn < actual){
      return {dateInvalid:true};
    }

    if(this.formulario != undefined && this.formulario.get('checkOut').valid){

      var checkOut = new Date(this.formulario.get('checkOut').value)
      checkOut.setDate(checkOut.getDate() + 1);
      checkOut.setHours(0, 0, 0, 0);

      if(checkOut>checkIn){
        return null;
      }else{
        return {dateInvalid:true};
      }

    }
    return null;
  }

  checkOutValidator(control:any){

    var checkOut = new Date(control.value)
    checkOut.setDate(checkOut.getDate() + 1);
    checkOut.setHours(0, 0, 0, 0);
    
    const actual = new Date();
    actual.setHours(0, 0, 0, 0);

    if(this.formulario != undefined && this.formulario.get('checkIn').valid){

      var checkIn = new Date(this.formulario.get('checkIn').value)
      checkIn.setDate(checkIn.getDate() + 1);
      checkIn.setHours(0, 0, 0, 0);

      if(checkOut>checkIn){
        return null;
      }

    }else if(checkOut >= actual){
      return null;
    }
    return {dateInvalid:true};
  }

  //Limpiar formulario
  limpiar(){
    this.impReserva = 0
    this.impServicio = 0
    this.impTotal = 0
    this.servicios_list=[]

    this.formulario.reset();
    this.formulario.patchValue({
      sede:0
    })
    this.listarServicios();
  }

  //Buscar
  filtrar(){
    var bean = new HabitacionDTO();
    bean.sede = this.sedeSeleccionada
    bean.tipo = this.tipoSeleccionado
    if(bean.sede == 0){
      Swal.fire("Error del Sistema","Debes seleccionar una sede","error")
      return
    } 
    this.SHabitacion.buscarXSedeAndTipo(bean).subscribe(
      (response)=> {
        this.mensaje='',
        this.habitaciones=response
      },
      (err)=>{this.habitaciones=null,this.mensaje=err.error.mensaje}
    )
  }
  
  //Llenar el formulario con la habitacion
  llenar(id:number){
    this.SHabitacion.buscar(id).subscribe(
      response =>{
        this.habitacion=response
        this.limpiar();
      },
      err =>{
        Swal.fire("Error de Habitación",err.error.mensaje,"error")
      }
    )
  }
  //Buscar clientes por nombre
  buscarClientes(){
    if(this.nombre){
      this.SCliente.buscarXNombre(this.nombre).subscribe(
        response =>{
          this.clientes=response
        }
      )
    }else{
      this.SCliente.listar().subscribe(
        response =>{
          this.clientes=response
        }
      )
    }
  }
  //Seleccionar cliente para el formulario
  seleccionarCliente(idCliente:number){
    this.SCliente.buscar(idCliente).subscribe(
      response =>{
        this.cliente=response
        this.formulario.patchValue({
          cliente:response.nombre
        })
      }
    )
  }
  //Calculamos el importe de la reserva
  importeReserva(){
    let checkIn = moment(this.formulario.get('checkIn').value)
    let checkOut = moment(this.formulario.get('checkOut').value)
    let dias = checkOut.diff(checkIn,'days')
    this.impReserva = dias * this.habitacion.tipo.costo  
    this.impTotal = this.impReserva
  }
  //Agregamos el servicio
  agregarServicio(bean:Servicio){
    let servicio = this.servicios_list.filter(x => x.id == bean.id)[0]
    
    if(servicio == null){
      this.servicios_list.push(bean)
      this.impServicio+=bean.costo
    }else{
      this.servicios_list = this.servicios_list.filter(x => x.id != bean.id)
      this.impServicio-=bean.costo
    }
    this.importeTotal()
  }

  //Calculamos importe total
  importeTotal(){
    this.impTotal=this.impReserva+this.impServicio
  }

  //Registramos la reservacion
  registrarReserva(){

    //Construimos la reservacion
    this.reservacion.checkIn = this.formulario.get("checkIn").value
    this.reservacion.checkOut = this.formulario.get("checkOut").value
    this.reservacion.cliente = this.cliente
    this.reservacion.empleado = this.empleado
    this.reservacion.habitacion = this.habitacion
    this.reservacion.importeReserva = this.impReserva
    this.reservacion.servicios = this.servicios_list
    this.reservacion.sede.id = this.sedeSeleccionada

    //Registramos reservacion
    this.SReservacion.registrar(this.reservacion).subscribe(
      response => {
        Swal.fire(response.mensaje,"La reservacion se registro","success"),
        setTimeout(() => {
          window.location.reload()
        },3000); 
      },
      err => Swal.fire("Error de registro",err.error.mensaje,"error")
    )
  }
}
