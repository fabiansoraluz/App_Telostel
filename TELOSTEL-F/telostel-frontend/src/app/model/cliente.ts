import { Ubigeo } from "./ubigeo"

export class Cliente{
    id:number=0
    nombre:string
    apellido:string
    dni:string
    celular:string
    createAt:Date
    estado:number
    ubigeo:Ubigeo = new Ubigeo()
}