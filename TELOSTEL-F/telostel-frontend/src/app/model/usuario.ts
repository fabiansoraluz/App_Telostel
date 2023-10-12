import { Empleado } from "./empleado"

export class Usuario {
    id:Number
    username:string
    password:string
    correo:string
    imagen:string
    createAt:Date
    estado:Number
    empleado:Empleado = new Empleado()
}
