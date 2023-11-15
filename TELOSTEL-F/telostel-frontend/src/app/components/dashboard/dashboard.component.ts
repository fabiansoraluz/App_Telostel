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
  public numClientes=0
  public numReservas=0
  public numHabitaciones=0
  public numProductos=0

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
      (response) => this.numClientes = response.length
    )
    this.SReserva.listar().subscribe(
      (response) => this.numReservas = response.length
    )
    this.SHabitacion.listar().subscribe(
      (response) => this.numHabitaciones = response.length
    )
    this.SProducto.listar().subscribe(
      (response) => this.numProductos = response.length
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

    // Obtenemos ventas de los ultimos 5 meses
    let ventas = [4,8,7,11,13]

    for(let i=0;i<5;i++){
      let dato = {x:meses[i],y:ventas[i]}
      datos.push(dato)
    }
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
}
