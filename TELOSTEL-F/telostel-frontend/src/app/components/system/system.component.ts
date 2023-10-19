import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-system',
  templateUrl: './system.component.html',
  styleUrls: ['./system.component.css']
})
export class SystemComponent implements OnInit{
  
  nombre:string
  rol:string
  titulo:string="Bienvenido a Telotel"

  constructor(
    private SToken:TokenService,
    private route: ActivatedRoute,
    private router:Router){}

  ngOnInit(): void {
    //Agregamos información del usuario
    this.nombre=this.SToken.getNombre();
    this.rol=this.SToken.getRol();
    //Agregamos el titulo
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        const rutaActivada = this.route.firstChild;
        if (rutaActivada && rutaActivada.snapshot.data['title']) {
          this.titulo = rutaActivada.snapshot.data['title'];
        }
      }
    });
  }

}
