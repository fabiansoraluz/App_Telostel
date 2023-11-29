import { Component, OnInit } from '@angular/core';
import { Habitacion } from '../model/habitacion';
import { Servicio } from '../model/servicio';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sede } from '../model/sede';
import { Reservacion } from '../model/reservacion';
import { Cliente } from '../model/cliente';
import { Empleado } from '../model/empleado';
import { HabitacionService } from '../services/habitacion.service';
import { ReservacionService } from '../services/reservacion.service';
import { EmpleadoService } from '../services/empleado.service';
import { ClienteService } from '../services/cliente.service';
import { TokenService } from '../services/token.service';
import * as moment from 'moment';
import Swal from 'sweetalert2';
import { Distrito } from '../model/distrito';
import { UbigeoService } from '../services/ubigeo.service';
import { Ubigeo } from '../model/ubigeo';

@Component({
  selector: 'app-reservacion',
  templateUrl: './reservacion.component.html',
  styleUrls: ['./reservacion.component.css']
})
export class ReservacionComponent implements OnInit{

  //Pisos y Habitaciones
  public habitaciones:[Habitacion]
  public pisos:number[]
  public pisoSeleccionado:number=0

  //Reservacion
  public reservacion= new Reservacion()
  public sedes:[Sede]
  public servicios:[Servicio]
  public formulario:FormGroup
  public habitacion:Habitacion
  public empleado:Empleado
  public ubigeo = new Ubigeo();

  //Cliente
  public cliente:Cliente;
  public existeCliente=false;

  //Importe
  public impReserva:number=0.00
  public impServicio:number=0.00
  public impTotal:number=0.00

  //Servicios del cliente
  public servicios_list:Servicio[] = []

  //Configuración de ubigeo
  departamentos:string[]
  departamento:string=""
  provincias:string[]
  provincia:string=""
  distritos:Distrito[]
  idUbigeo:string=""

  constructor(
    private SHabitacion:HabitacionService,
    private SReservacion:ReservacionService,
    private SUbigeo:UbigeoService,
    private SCliente:ClienteService,
    private formBuilder:FormBuilder
  ){}

  ngOnInit(): void {
    //Buscar habitaciones del primer piso
    this.SHabitacion.buscarXPiso(1).subscribe(
      response =>{
        this.habitaciones = response.filter(h => h.estado === 'disponible')
      }
    )
    //Listar departamentos
    this.SUbigeo.departamentos().subscribe(
      response => this.departamentos=response
    )
    //Buscar último piso
    this.SHabitacion.ultimoPiso().subscribe(
      response =>{
        if(response!=0){
          this.pisos = Array.from({length: response},(_,i) => i + 1);
        }
      }
    )
    //Listar Sedes
    this.SReservacion.sedes().subscribe(
      response =>{
        this.sedes = response
      }
    )
    //Listar Servicios
    this.listarServicios();

    //Construir formulario
    this.formulario = this.formBuilder.group({
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
      ]],
      sede:[0,
      [
        Validators.required
      ]],
      consulta:['']
    })
  }

  public listarProvincias(){
    this.provincia=""
    this.ubigeo.id=""
    this.SUbigeo.provincias(this.departamento).subscribe(
      response => this.provincias=response
    )
  }

  public listarDistritos(){
    this.ubigeo.id=""
    this.SUbigeo.distrito(this.provincia).subscribe(
      response => this.distritos = response
    )
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
  //Cambiar Piso
  cambiarPiso(){
    this.SHabitacion.buscarXPiso(this.pisoSeleccionado).subscribe(
      response =>{
        this.habitaciones = response.filter(h => h.estado === 'disponible')
      }
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
    //Validamos si el cliente existe
    if(this.existeCliente){
      this.reservar(this.cliente)
    }else{
      //Construimos al Cliente
      var cliente = this.buildCliente()
      //Registramos al Cliente y la Reserva
      this.registrarCliente(cliente);
    }
  }
  buildCliente(){
    var bean = new Cliente();
    bean.nombre=this.formulario.get("nombre").value
    bean.apellido=this.formulario.get("apellido").value
    bean.celular=this.formulario.get("celular").value
    bean.dni=this.formulario.get("dni").value
    bean.ubigeo = this.ubigeo
    return bean
  }
  registrarCliente(bean:Cliente){
    this.SCliente.registrar(bean).subscribe(
      (response)=>{
        this.reservar(response)
      },(err)=>Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }
  reservar(bean:Cliente){
    //Construimos la reservacion
    this.reservacion.checkIn        = this.formulario.get("checkIn").value
    this.reservacion.checkOut       = this.formulario.get("checkOut").value
    this.reservacion.cliente        = bean
    this.reservacion.empleado       = this.empleado
    this.reservacion.habitacion     = this.habitacion
    this.reservacion.importeReserva = this.impReserva
    this.reservacion.servicios      = this.servicios_list
    this.reservacion.sede.id        = this.formulario.get("sede").value
    
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
  buscarCliente(){
    this.SCliente.buscarXDNI(this.formulario.get("consulta").value).subscribe(
      (response)=>{
        this.cliente=response,
        this.formulario.patchValue({
          nombre:this.cliente.nombre,
          apellido:this.cliente.apellido,
          celular:this.cliente.celular,
          dni:this.cliente.dni,
          ubigeo:0
        })
        this.ubigeo.id=this.cliente.ubigeo.id
        this.existeCliente=true
      },
      (err)=>Swal.fire("Error del Sistema",err.error.mensaje,"error")
    )
  }
}

