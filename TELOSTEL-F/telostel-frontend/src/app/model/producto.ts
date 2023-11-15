import { Categoria } from "./categoria"

export class Producto {
    id:number = 0;
    nombre:string = "";
    cantUnidad:string = "";
    precio:number = 0;
    stock:number = 0;
    createAt:Date
    estado:number = 0;
    categoria: Categoria = new Categoria();
}
