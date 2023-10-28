import { Categoria } from "./categoria"

export class Carrito {

    idProducto:number
    producto:string
    categoria:Categoria
    precio:number
    cantidad:number
    importe:number

    constructor(idProducto:number, producto:string,categoria:Categoria,precio:number,cantidad:number,importe:number){
        this.idProducto=idProducto
        this.producto=producto
        this.categoria=categoria
        this.precio=precio
        this.cantidad=cantidad
        this.importe=importe
    }

}
