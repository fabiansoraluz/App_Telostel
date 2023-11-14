import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/model/categoria';
import { Producto } from 'src/app/model/producto';
import { ProductoService } from 'src/app/services/producto.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mantenimiento-producto',
  templateUrl: './mantenimiento-producto.component.html',
  styleUrls: ['./mantenimiento-producto.component.css']
})
export class MantenimientoProductoComponent implements OnInit {

  formulario: FormGroup
  productos: Producto[] = []
  producto: Producto = new Producto()
  isUpdate: boolean

  categorias: Categoria[]
  categoria: string = ""

  error_lista: String

  constructor(
    private SProducto: ProductoService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.listar()
    this.listarCategorias();

    this.formulario = this.formBuilder.group({
      nombre: ['', [
        Validators.required,
        Validators.pattern("[a-zA-ZáéíóúÁÉÍÓÚñ\\s]{3,30}")
      ]],
      cantUnidad: ['', [
        Validators.required,
        Validators.pattern("[a-zA-ZáéíóúÁÉÍÓÚñ\\s]{4,30}")
      ]],
      precio: ['', [
        Validators.required,
        Validators.pattern("[\\d]{1,9}")
      ]],
      stock: ['', [
        Validators.required,
        Validators.pattern("[\\d]{1,9}")
      ]],
      categoria: ['', [
        Validators.required
      ]]
    })
  }

  public listar() {
    this.SProducto.listar().subscribe(
      (response) => this.productos = response,
      (err) => this.error_lista = err.error.mensaje
    )
  }

  public listarCategorias() {
    this.SProducto.listarCategorias().subscribe(
      (categorias: Categoria[]) => {
        this.categorias = categorias;
      },
      (error) => {
        console.error('Error al obtener categorías:', error);
      }
    );
  }


  public llenarDatos(id: number) {
    this.SProducto.buscar(id).subscribe(
      (response) => {

        this.formulario.patchValue({
          id: response.id,
          nombre: response.nombre,
          cantUnidad: response.cantUnidad,
          precio: response.precio,
          stock: response.stock
        })

        this.categoria = response.categoria.nombre
        this.listarCategorias()
        this.producto = response
        this.isUpdate = true
      },
      (err) => Swal.fire("Error del Sistema", err.error.mensaje, "error")
    )
  }


  public eliminar(id: number) {
    Swal.fire({
      title: 'Eliminar Producto',
      text: "¿Seguro que deseas eliminar el Producto?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.SProducto.eliminar(id).subscribe(
          (response) => {
            Swal.fire("Cliente Eliminada", response.mensaje, "success")
            this.listar();
          },
          (err) => {
            Swal.fire("Error del Sistema", err.error.mensaje, "error")
          }

        )
      }
    })
  }

}
