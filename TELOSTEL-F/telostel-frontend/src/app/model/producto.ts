import { Categoria } from "./categoria"

export class Producto {
    id:number
    nombre:string = "";
    cantUnidad:string = "";
    precio:number = 0;
    stock:number = 0;
    createAt:Date
    estado:number = 0;
    categoria: Categoria = new Categoria();
}
