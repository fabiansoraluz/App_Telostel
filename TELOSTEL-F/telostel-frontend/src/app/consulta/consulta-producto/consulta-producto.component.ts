import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/app/model/categoria';
import { ConsultaProducto } from 'src/app/model/consulta-producto';
import { Producto } from 'src/app/model/producto';
import { ProductoService } from 'src/app/services/producto.service';

@Component({
  selector: 'app-consulta-producto',
  templateUrl: './consulta-producto.component.html',
  styleUrls: ['./consulta-producto.component.css']
})
export class ConsultaProductoComponent implements OnInit{

  public productos:[Producto]
  public categorias:[Categoria]
  public consulta:ConsultaProducto=new ConsultaProducto()
  public mensaje:string

  constructor(
    private SProducto:ProductoService
  ){}

  ngOnInit(): void {
    this.SProducto.listar().subscribe(
      (response) => this.productos=response,
      (err) => this.mensaje=err.error.mensaje
    )
    
    this.SProducto.listarCategorias().subscribe(
      response => this.categorias=response
    )
    
  }
  consultar(){
    this.SProducto.consulta(this.consulta).subscribe(
      (response) => {this.productos=response,this.mensaje=null},
      (err) => this.mensaje=err.error.mensaje
    )
  }
  limpiar(){
    window.location.reload();
  }

}
