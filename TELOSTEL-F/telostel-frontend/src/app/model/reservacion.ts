import { Cliente } from "./cliente"
import { Empleado } from "./empleado"
import { Habitacion } from "./habitacion"
import { Sede } from "./sede"
import { Servicio } from "./servicio"

export class Reservacion{
    id:number
    checkIn:Date
    checkOut:Date
    importeReserva:number
    createAt:Date
    estado:number
    cliente:Cliente
    empleado:Empleado
    habitacion:Habitacion
    sede:Sede = new Sede()
    servicios:Servicio[]
    
}