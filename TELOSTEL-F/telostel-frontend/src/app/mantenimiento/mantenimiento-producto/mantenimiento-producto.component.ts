import { Component, OnInit } from '@angular/core';
import { Producto } from 'src/app/model/producto';
import { ProductoService } from 'src/app/services/producto.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mantenimiento-producto',
  templateUrl: './mantenimiento-producto.component.html',
  styleUrls: ['./mantenimiento-producto.component.css']
})
export class MantenimientoProductoComponent implements OnInit {

  public productos:[Producto]

  constructor(
    private SProducto:ProductoService
  
  ){}
  ngOnInit(): void {
    this.SProducto.listar().subscribe(
      response => this.productos=response
    )
  }
}
