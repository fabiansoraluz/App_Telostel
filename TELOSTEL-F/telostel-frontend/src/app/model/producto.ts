import { Categoria } from "./categoria"

export class Producto {
    id:number
    nombre:string
    cantUnidad:string
    precio:number
    stock:number
    createAt:Date
    estado:number
    categoria: Categoria = new Categoria;
}
