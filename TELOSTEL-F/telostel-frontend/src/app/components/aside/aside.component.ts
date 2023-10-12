import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.css']
})
export class AsideComponent {

  constructor(private SToken:TokenService,private router:Router){}

  logout(){
    this.SToken.logout();
    this.router.navigate(["/login"])
    setTimeout(() => {
      window.location.reload();
    },5); 
  }

}
