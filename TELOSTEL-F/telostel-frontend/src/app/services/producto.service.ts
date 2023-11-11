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

  public registrar(bean: Producto): Observable<any> {
    return this.http.post(`${this.host}`, bean);
  }

  public actualizar(bean: Producto): Observable<any> {
    return this.http.put(`${this.host}`, bean);
  }

  public eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.host}/${id}`);
  }

  public buscarXNombre(nombre: string): Observable<any> {
    return this.http.get(`${this.host}/nombre/${nombre}`);
  }
}
