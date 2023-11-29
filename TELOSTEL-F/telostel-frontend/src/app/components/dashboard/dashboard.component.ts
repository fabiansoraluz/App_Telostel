import { Component,OnInit } from '@angular/core';
import Swal from 'sweetalert2';

import {
  ApexAxisChartSeries,
  ApexChart,
  ApexDataLabels,
  ApexPlotOptions,
  ApexLegend
} from "ng-apexcharts";
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';
import { ClienteService } from 'src/app/services/cliente.service';
import { ReservacionService } from 'src/app/services/reservacion.service';
import { HabitacionService } from 'src/app/services/habitacion.service';
import { ProductoService } from 'src/app/services/producto.service';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  dataLabels: ApexDataLabels;
  plotOptions: ApexPlotOptions;
  legend: ApexLegend;
  colors: string[];
};

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  
  public chartOptions: Partial<ChartOptions>;
  public numClientes:number
  public numReservas:number
  public numHabitaciones:number
  public numProductos:number

  constructor(
    private router:Router,
    private SToken:TokenService,
    private SCliente:ClienteService,
    private SReserva:ReservacionService,
    private SHabitacion:HabitacionService,
    private SProducto:ProductoService
  ){}

  ngOnInit(): void {
    this.generarGrafico()
    this.SCliente.listar().subscribe(
      (response) => this.numClientes = response.length,
      (err)=>this.numClientes=0
    )
    this.SReserva.listar().subscribe(
      (response) => this.numReservas = response.length,
      (err)=>this.numReservas=0
    )
    this.SHabitacion.listar().subscribe(
      (response) => this.numHabitaciones = response.length,
      (err)=>this.numHabitaciones=0
    )
    this.SProducto.listar().subscribe(
      (response) => this.numProductos = response.length,
      (err)=>this.numProductos=0
    )
  }

  ultimosMeses():string[]{
    const mesesEnEspanol = [
      'enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio',
      'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre'
    ];
  
    const fechaActual = new Date();
    const ultimos5Meses = [];
  
    for (let i = 0; i < 5; i++) {
      const mesIndex = (fechaActual.getMonth() - i + 12) % 12;
      ultimos5Meses.push(mesesEnEspanol[mesIndex]);
    }
  
    return ultimos5Meses;
  }

  obtenerDatos():any[]{

    // Generamos arreglo para guardar los datos
    var datos:any[] = []

    // Obtenemos los ultimos 5 meses
    let meses = this.ultimosMeses().reverse()

    // Obtenemos reservas de los ultimos 5 meses
    this.SReserva.datosEstadisticos().subscribe(
      (response) => {
        for(let i=0;i<5;i++){
          let dato = {x:meses[i],y:response[i].total}
          datos.push(dato)
        }
      }
    )
    
    return datos
  }

  generarGrafico(){
    this.chartOptions = {
      series: [
        {
          name: "Reservas",
          data: this.obtenerDatos()
        }
      ],
      chart: {
        height: 350,
        type: "bar"
      },
      plotOptions: {
        bar: {
          columnWidth: "50%"
        }
      },
      colors: ["#00E396"],
      dataLabels: {
        enabled: false
      },
      legend: {
        show: true,
        showForSingleSeries: true,
        customLegendItems: ["Reservas Registradas"],
        markers: {
          fillColors: ["#00E396"]
        }
      }
    };
  }
  //Enlaces
  mantenimientoEnlace(enlace:String){
    let rolesPermitidos=["Administrador","Editor"]
    let rol = this.SToken.getRol()
    if(rolesPermitidos.includes(rol)){
      this.router.navigate([enlace])
    }else{
      Swal.fire("Error de Permisos","Usted no tiene permisos necesarios","error")
    }
  }
  serviciosEnlace(enlace:String){
    let rolesPermitidos=["Administrador","Recepcion"]
    let rol = this.SToken.getRol()
    if(rolesPermitidos.includes(rol)){
      this.router.navigate([enlace])
    }else{
      Swal.fire("Error de Permisos","Usted no tiene permisos necesarios","error")
    }
  }
  consultarEnlace(enlace:String){
    let rolesPermitidos=["Administrador","Consultor","Recepcion"]
    let rol = this.SToken.getRol()
    if(rolesPermitidos.includes(rol)){
      this.router.navigate([enlace])
    }else{
      Swal.fire("Error de Permisos","Usted no tiene permisos necesarios","error")
    }
  }
}
