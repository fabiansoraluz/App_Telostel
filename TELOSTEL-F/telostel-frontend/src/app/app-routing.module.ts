import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrarComponent } from './components/rr/registrar/registrar.component';
import { RecuperarComponent } from './components/rr/recuperar/recuperar.component';
import { RRComponent } from './components/rr/rr.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SystemComponent } from './components/system/system.component';

const routes: Routes = [
  {path:'',redirectTo:'/login',pathMatch:"full"},
  {path:'login', component:LoginComponent},
  {path:'opciones',component:RRComponent,children:[
    {path:'registrar',component:RegistrarComponent},
    {path:'recuperar',component:RecuperarComponent}
  ]},
  {path:'system',component:SystemComponent,children:[
    {path:'dashboard',component:DashboardComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
