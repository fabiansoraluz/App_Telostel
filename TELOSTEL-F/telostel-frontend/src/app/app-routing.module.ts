import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrarComponent } from './components/rr/registrar/registrar.component';
import { RecuperarComponent } from './components/rr/recuperar/recuperar.component';
import { RRComponent } from './components/rr/rr.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SystemComponent } from './components/system/system.component';

import { PerfilComponent } from './components/perfil/perfil.component';
import { ReporteReservacionComponent } from './reporte/reporte-reservacion/reporte-reservacion.component';
import { ReporteVentaComponent } from './reporte/reporte-venta/reporte-venta.component';
import { ConsultaClienteComponent } from './consulta/consulta-cliente/consulta-cliente.component';
import { ConsultaProductoComponent } from './consulta/consulta-producto/consulta-producto.component';
import { ConsultaReservacionComponent } from './consulta/consulta-reservacion/consulta-reservacion.component';
import { ServicioReservacionComponent } from './servicio/servicio-reservacion/servicio-reservacion.component';
import { ServicioVentaComponent } from './servicio/servicio-venta/servicio-venta.component';

import { AsideComponent } from './components/aside/aside.component';
import { MantenimientoProductoComponent } from './mantenimiento/mantenimiento-producto/mantenimiento-producto.component';
import { MantenimientoHabitacionComponent } from './mantenimiento/mantenimiento-habitacion/mantenimiento-habitacion.component';
import { MantenimientoClienteComponent } from './mantenimiento/mantenimiento-cliente/mantenimiento-cliente.component';


const routes: Routes = [
  {path:'',redirectTo:'/login',pathMatch:"full"},
  {path:'login', component:LoginComponent},
  {path:'aside', component:AsideComponent},
  {path:'opciones',component:RRComponent,children:[
    {path:'registrar',component:RegistrarComponent},
    {path:'recuperar',component:RecuperarComponent}
  ]},
  {path:'system',component:SystemComponent,children:[
    {path:'dashboard',component:DashboardComponent,data:{title:'Bienvenido a TELOTEL'}},
    {path:'perfil',component:PerfilComponent,data:{title:'Configuración del Usuario'}},
    {path:'consulta/cliente',component:ConsultaClienteComponent,data:{title:'Consulta de Clientes'}},
    {path:'consulta/producto',component:ConsultaProductoComponent,data:{title:'Consulta de Productos'}},
    {path:'consulta/reservacion',component:ConsultaReservacionComponent,data:{title:'Consulta de Reservaciones'}},
    {path:'mantenimiento/habitacion',component:MantenimientoHabitacionComponent,data:{title:'Mantenimiento de Habitaciones'}},
    {path:'mantenimiento/producto',component:MantenimientoProductoComponent,data:{title:'Mantenimiento de Productos'}},
    {path:'mantenimiento/cliente',component:MantenimientoClienteComponent,data:{title:'Mantenimiento de Clientes'}},
    {path:'reporte/reservacion',component:ReporteReservacionComponent,data:{title:'Reporte de Reservaciones'}},
    {path:'reporte/venta',component:ReporteVentaComponent,data:{title:'Reporte de Ventas'}},
    {path:'servicio/reservacion',component:ServicioReservacionComponent,data:{title:'Reserva con nosotros'}},
    {path:'servicio/venta',component:ServicioVentaComponent,data:{title:'Tienda Local'}}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
