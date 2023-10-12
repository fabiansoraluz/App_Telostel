import { Component,OnInit } from '@angular/core';
import { UtilesService } from 'src/app/services/utiles.service';

@Component({
  selector: 'app-recuperar',
  templateUrl: './recuperar.component.html',
  styleUrls: ['./recuperar.component.css']
})
export class RecuperarComponent implements OnInit{
  
  constructor(private SUtiles:UtilesService){}

  ngOnInit(): void {
    //Implementamos función de password
    this.SUtiles.password();
  }

}
