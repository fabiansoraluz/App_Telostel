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
        Validators.pattern("^[a-zA-Z0-9\\s]{4,40}$")
      ]],
      precio: ['', [
        Validators.required,
        Validators.pattern("^[0-9]+(\.[0-9]+)?$")
      ]],
      stock: ['', [
        Validators.required,
        Validators.pattern("^[0-9]+$")
      ]],
      categoria: ['', [
        Validators.required
      ]]
    })

  }

  public listar() {
    this.SProducto.listar().subscribe(
      (response) => {
        this.productos = response;
      },
      (err) => this.error_lista = err.error.mensaje
    );
  }

  public listarCategorias() {
    this.SProducto.listarCategorias().subscribe(
      (categorias: Categoria[]) => {
        this.categorias = categorias;
      }
    );
  }


  public llenarDatos(id: number) {
    this.SProducto.buscar(id).subscribe(
      (response) => {
        console.log(response)
        this.formulario.patchValue({
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


  public registrar() {
    if (this.producto.categoria.id === 0) {
      Swal.fire("Error de Ubigeo", "Debes seleccionar un ubigeo", "error")
      return
    }
    this.buildProducto();
    this.SProducto.registrarProducto(this.producto).subscribe(
      (response) => {
        Swal.fire("Producto Registrado", `El producto ${response.nombre} ha sido registrado`, "success")
        setTimeout(() => {
          window.location.reload();
        }, 3000);
      },
      (err) => Swal.fire("Error de Sistema", err.error.mensaje, "error")
    )
  }


  public actualizar() {
    if (this.producto.categoria.id === 0) {
      Swal.fire("Error de Categoria", "Debes seleccionar una Categoria", "error");
      return;
    }
    this.buildProducto();
    this.SProducto.actualizarProducto(this.producto.id, this.producto).subscribe(
      (response) => {
        Swal.fire("Producto Actualizado", `El producto ${response.nombre} ha sido actualizado`, "success");
        this.listar();
        this.formulario.reset();
        this.isUpdate = false;
      },
      (err) => Swal.fire("Error del Sistema", err.error.mensaje, "error")
    );
  }


  public buildProducto() {
    this.producto.nombre = this.formulario.get('nombre').value;
    this.producto.cantUnidad = this.formulario.get('cantUnidad').value;
    this.producto.precio = this.formulario.get('precio').value;
    this.producto.stock = this.formulario.get('stock').value;
    this.producto.categoria.id = +this.formulario.get('categoria').value;


  }

}
