import { Cliente } from "./cliente";
import { Empleado } from "./empleado";

export class Venta{

    id:number
    importe:number
    cliente:Cliente
    empleado:Empleado
    createAt:Date
}