import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Carrito } from 'src/app/model/carrito';
import { Cliente } from 'src/app/model/cliente';
import { Empleado } from 'src/app/model/empleado';
import { Producto } from 'src/app/model/producto';
import { Venta } from 'src/app/model/venta';
import { ClienteService } from 'src/app/services/cliente.service';
import { EmpleadoService } from 'src/app/services/empleado.service';
import { ProductoService } from 'src/app/services/producto.service';
import { TokenService } from 'src/app/services/token.service';
import { VentaService } from 'src/app/services/venta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-servicio-venta',
  templateUrl: './servicio-venta.component.html',
  styleUrls: ['./servicio-venta.component.css']
})
export class ServicioVentaComponent implements OnInit{
  
  //Formulario
  public formulario:FormGroup
  public fecha:String
  private empleado:Empleado
  private cliente:Cliente

  //Buscar Cliente
  public clientes:[Cliente]
  public nombre_cliente:string

  //Buscar Productos
  private id_producto:number
  public producto:Producto = new Producto()
  public nombre_producto:string
  public productos:[Producto]
  public cantidad:number=1

  //Carrito
  public carrito:Carrito[]=[]
  public importeTotal:number=0

  constructor(
    private SEmpleado:EmpleadoService,
    private SCliente:ClienteService,
    private SProducto:ProductoService,
    private SVenta:VentaService,
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

    //Setteamos la fecha actual
    const fechaActual = new Date();
    fechaActual.setDate(fechaActual.getDate() - 1); // Disminuye un día
    this.fecha = fechaActual.toISOString().slice(0, 10); 

    //Listar Clientes
    this.SCliente.listar().subscribe(
      response =>{
        this.clientes=response
      }
    )
    //Listar Productos
    this.SProducto.listar().subscribe(
      response =>{
        this.productos=response
      }
    )
    
    //Construir formulario
    this.formulario = this.formBuilder.group({
      cliente:['',
      [
        Validators.required
      ]]
    })

  }
  //Buscar clientes por nombre
  buscarClientes(){
    if(this.nombre_cliente){
      this.SCliente.buscarXNombre(this.nombre_cliente).subscribe(
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

  //Buscar productos por nombre
  buscarProductos(){
    if(this.nombre_producto){
      this.SProducto.buscarXNombre(this.nombre_producto).subscribe(
        response => this.productos=response
      )
    }else{
      this.SProducto.listar().subscribe(
        response =>{
          this.productos=response
        }
      )
    }
  }
  //Seleccionar Producto
  seleccionarProducto(bean:Producto){
    this.producto=bean
  }
  //Agregar producto al carrito
  agregarProducto(){

    if(!this.producto.id){
      Swal.fire("Error del Modal","Debes seleccionar un producto","error")
      return
    }

    if((this.producto.stock-this.cantidad)<0){
      Swal.fire("Error de Stock","No hay stock suficiente","error")
      return
    }

    if(this.carrito.length>0 && this.carrito.some(x=>x.idProducto==this.producto.id)){
      const indice = this.carrito.findIndex(x => x.idProducto == this.producto.id);
      let item = this.carrito[indice]
      item.cantidad+=this.cantidad
      item.importe= item.cantidad*item.precio
    }else{
      //Calculamos el importe
      let importe = this.producto.precio * this.cantidad
      //Instanciamos un item de carrito
      var bean = new Carrito(this.producto.id,this.producto.nombre,this.producto.categoria,this.producto.precio,this.cantidad,importe);
      //Agregamos item
      this.carrito.push(bean)
    }
    //Setteamos el producto y la cantidad
    this.producto = new Producto()
    this.cantidad = 1

    //Calcular importe total
    this.calcularTotal()
  }

  //Eliminamos producto
  eliminarProducto(bean:Carrito){
    let index = this.carrito.indexOf(bean);
    this.carrito.splice(index, 1);
    this.calcularTotal()
  }

  //Calculamos el importe total
  calcularTotal(){
    var total=0
    this.carrito.forEach(item =>{
      total+=item.importe
    })
    this.importeTotal=total
  }

  //Registramos venta
  registrar(){
    let venta = new Venta()
    venta.cliente=this.cliente
    venta.empleado=this.empleado
    venta.importe=this.importeTotal
    this.SVenta.registrar(venta,this.carrito)
    Swal.fire("Venta Registrada","La venta se registro exitosamente","success")
    setTimeout(() => {
      this.limpiar()
    }, 3000); 
  }
  
  limpiar(){
    this.carrito=[]
    this.formulario.reset()
    this.calcularTotal()
    window.location.reload();
  }
}
