import { Component,OnInit } from '@angular/core';
import { UtilesService } from '../../services/utiles.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  
  constructor(private SUtiles:UtilesService){}

  ngOnInit(): void {
    //Función del sidebar
    this.SUtiles.sidebar();
  }

}
