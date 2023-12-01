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
    cliente:Cliente = new Cliente();
    empleado:Empleado = new Empleado();
    habitacion:Habitacion = new Habitacion();
    sede:Sede = new Sede()
    servicios:Servicio[] = []
    
}