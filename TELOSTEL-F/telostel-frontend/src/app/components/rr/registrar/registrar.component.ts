import { Component,OnInit } from '@angular/core';
import { UtilesService } from 'src/app/services/utiles.service';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit{
  
  constructor(private SUtiles:UtilesService){}

  ngOnInit(): void {
    //Implementamos función de password
    this.SUtiles.password();
  }

}
