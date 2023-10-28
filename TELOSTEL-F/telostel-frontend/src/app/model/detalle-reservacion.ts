import { Reservacion } from "./reservacion";
import { Servicio } from "./servicio";

export class DetalleReservacion{

    reservacion:Reservacion
    servicio:Servicio
    descripcion:string
    costo:number
    cantidad:number
    importe:number

}