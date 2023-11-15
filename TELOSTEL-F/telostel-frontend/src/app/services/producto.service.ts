import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../model/producto';
import { TokenService } from './token.service';
import { Categoria } from '../model/categoria';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private host = "http://localhost:8080/api/producto"



  constructor(private http:HttpClient) { }
  public listar():Observable<any>{
    return this.http.get(this.host)

  constructor(private http: HttpClient) { }

  
  // Métodos relacionados con CategoriaProducto

  public listarCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.host}/categorias`);
  }

  public buscarCategoriaXId(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.host}/categorias/${id}`);
  }

  // CRUD
  public listar(): Observable<any> {
    return this.http.get(`${this.host}/productos`);
  }

  public buscar(id: number): Observable<any> {
    return this.http.get(`${this.host}/buscar/${id}`);
  }

  public registrarProducto(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(`${this.host}/registrar`, producto);
  }

  public actualizarProducto(id: number, producto: Producto): Observable<Producto> {
    return this.http.put<Producto>(`${this.host}/actualizar/${id}`, producto);
  }

  public eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.host}/${id}`);
  }

  public buscarXNombre(nombre: string): Observable<any> {
    return this.http.get(`${this.host}/nombre/${nombre}`);
  }

  //listar categorias
  public listarCategorias():Observable<any>{
    return this.http.get(this.host+"/categorias")
  }

  public productoXCategoria(id:number):Observable<any>{
    return this.http.get(this.host+"/categorias/"+id)
  }
}
