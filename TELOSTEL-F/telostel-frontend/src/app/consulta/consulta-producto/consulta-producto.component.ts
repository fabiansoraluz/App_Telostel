import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/app/model/categoria';
import { Producto } from 'src/app/model/producto';
import { ProductoService } from 'src/app/services/producto.service';

@Component({
  selector: 'app-consulta-producto',
  templateUrl: './consulta-producto.component.html',
  styleUrls: ['./consulta-producto.component.css']
})
export class ConsultaProductoComponent implements OnInit{

  public productos:[Producto]
  public nombre = ''
  public categorias:[Categoria]
  public categoriaSeleccionada:number=0



  constructor(
    private SProducto:ProductoService
  ){}

  ngOnInit(): void {
    this.SProducto.listar().subscribe(
      response => this.productos=response
    )
    
    this.SProducto.listarCategorias().subscribe(
      response => this.categorias=response
    )
    
  }

  productoPorCategoria(){
    this.SProducto.productoXCategoria(this.categoriaSeleccionada).subscribe(
      response =>this.productos = response   
    )
  }

}
