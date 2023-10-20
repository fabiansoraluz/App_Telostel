import { TipoHabitacion } from "./tipohabitacion"


export class Habitacion {
    id:number
    numero:number
    piso:number
    createAt:Date
    estado:number
    tipohabitacion:TipoHabitacion = new TipoHabitacion()
}