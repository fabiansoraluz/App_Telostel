import { Component,OnInit } from '@angular/core';
import { UtilesService } from '../../services/utiles.service';

import {
  ApexAxisChartSeries,
  ApexChart,
  ApexDataLabels,
  ApexPlotOptions,
  ApexLegend
} from "ng-apexcharts";

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

  constructor(private SUtiles:UtilesService){}

  ngOnInit(): void {
    this.generarGrafico()
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
          name: "Ventas",
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

}
