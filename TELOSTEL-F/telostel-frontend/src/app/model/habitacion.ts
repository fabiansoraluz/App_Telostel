import { Hotel } from "./hotel";
import { TipoHabitacion } from "./tipo-habitacion"

export class Habitacion {

    id:number = 0;
    numero:number = 0;
    piso:number = 0;
    createAt:Date
    estado:string = "";
    hotel:Hotel = new Hotel();
    tipo:TipoHabitacion = new TipoHabitacion()
}
