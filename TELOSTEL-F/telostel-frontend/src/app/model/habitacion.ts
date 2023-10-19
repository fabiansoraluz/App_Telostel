import { TipoHabitacion } from "./tipo-habitacion"

export class Habitacion {

    id:number
    numero:number
    piso:number
    createAt:Date
    estado:string
    tipo:TipoHabitacion = new TipoHabitacion()
}
