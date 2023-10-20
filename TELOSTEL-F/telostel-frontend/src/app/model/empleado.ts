import { Ubigeo } from "./ubigeo"


export class Empleado {
    id:number
    nombre:string
    apellido:string
    dni:string
    celular:string
    createAt:Date
    estado:number
    ubigeo:Ubigeo = new Ubigeo()
}
