import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/model/cliente';
import { ConsultaCliente } from 'src/app/model/consulta-cliente';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-consulta-cliente',
  templateUrl: './consulta-cliente.component.html',
  styleUrls: ['./consulta-cliente.component.css']
})
export class ConsultaClienteComponent implements OnInit{

  clientes:Cliente[]=[]
  consulta:ConsultaCliente=new ConsultaCliente()
  //Mensaje: No hay clientes registrado
  error_lista:String

  constructor(
    private SCliente:ClienteService
  ){}
  ngOnInit(): void {
    //Listamos los clientes
    this.listar()
  }
  public listar(){
    this.SCliente.listar().subscribe(
      (response) => this.clientes=response,
      (err)=> this.error_lista=err.error.mensaje
    )
  }
  public consultar(){
    this.SCliente.consulta(this.consulta).subscribe(
      (response) => {this.clientes=response,this.error_lista=null},
      (err)=> this.error_lista=err.error.mensaje
    )
  }
  public limpiar(){
    window.location.reload();
  }
}
