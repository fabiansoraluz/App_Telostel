import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Enlace } from 'src/app/model/enlace';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.css']
})
export class AsideComponent implements OnInit{

  enlaces:Enlace[]

  constructor(private SToken:TokenService,private router:Router){}

  ngOnInit(): void {
    this.enlaces=this.SToken.getEnlaces()
  }

  logout(){
    this.SToken.logout();
    this.router.navigate(["/login"])
    setTimeout(() => {
      window.location.reload();
    },5); 
  }
  sidebar(event:MouseEvent){
    const button = event.currentTarget as HTMLButtonElement;
    const arrow = button.querySelector(".sidebar__arrow")
    arrow.classList.toggle("sidebar__arrow--active")

    const menu = button.nextElementSibling as HTMLElement;
    let height = 0;
    if(menu.clientHeight == 0){
        height=menu.scrollHeight;
    }
    menu.style.height = `${height}px`
  }
}
