import { TipoHabitacion } from "./tipo-habitacion"

export class Habitacion {

    id:number = 0;
    numero:number = 0;
    piso:number = 0;
    createAt:Date
    estado:string = "";
    tipo:TipoHabitacion = new TipoHabitacion()
}
