import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-system',
  templateUrl: './system.component.html',
  styleUrls: ['./system.component.css']
})
export class SystemComponent implements OnInit{
  
  nombre:string
  rol:string

  constructor(private SToken:TokenService){}

  ngOnInit(): void {
    this.nombre=this.SToken.getNombre();
    this.rol=this.SToken.getRol();
  }

}
