import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrarComponent } from './components/rr/registrar/registrar.component';
import { RecuperarComponent } from './components/rr/recuperar/recuperar.component';
import { RRComponent } from './components/rr/rr.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SystemComponent } from './components/system/system.component';

import { PerfilComponent } from './components/perfil/perfil.component';
import { MantenimientoClienteComponent } from './mantenimiento/mantenimiento-cliente/mantenimiento-cliente.component';
import { MantenimientoEmpleadoComponent } from './mantenimiento/mantenimiento-empleado/mantenimiento-empleado.component';
import { ReporteReservacionComponent } from './reporte/reporte-reservacion/reporte-reservacion.component';
import { ReporteVentaComponent } from './reporte/reporte-venta/reporte-venta.component';
import { ConsultaClienteComponent } from './consulta/consulta-cliente/consulta-cliente.component';
import { ConsultaProductoComponent } from './consulta/consulta-producto/consulta-producto.component';
import { ConsultaReservacionComponent } from './consulta/consulta-reservacion/consulta-reservacion.component';
import { ServicioReservacionComponent } from './servicio/servicio-reservacion/servicio-reservacion.component';
import { ServicioVentaComponent } from './servicio/servicio-venta/servicio-venta.component';

import { AsideComponent } from './components/aside/aside.component';
import { MantenimientoHabitacionComponent } from './mantenimiento/mantenimiento-habitacion/mantenimiento-habitacion.component';
import { RegistrarHabitacionComponent } from './mantenimiento/mantenimiento-habitacion/registrar-habitacion/registrar-habitacion.component';
import { ListarHabitacionComponent } from './mantenimiento/mantenimiento-habitacion/listar-habitacion/listar-habitacion.component';


const routes: Routes = [
  {path:'',redirectTo:'/login',pathMatch:"full"},
  {path:'login', component:LoginComponent},
  {path:'aside', component:AsideComponent},
  {path:'opciones',component:RRComponent,children:[
    {path:'registrar',component:RegistrarComponent},
    {path:'recuperar',component:RecuperarComponent}
  ]},
  {path:'system',component:SystemComponent,children:[
    {path:'dashboard',component:DashboardComponent},
    {path:'perfil',component:PerfilComponent},
    {path:'consulta/cliente',component:ConsultaClienteComponent},
    {path:'consulta/producto',component:ConsultaProductoComponent},
    {path:'consulta/reservacion',component:ConsultaReservacionComponent},
    {path:'mantenimiento/cliente',component:MantenimientoClienteComponent},
    {path:'mantenimiento/empleado',component:MantenimientoEmpleadoComponent},
    {path:'mantenimiento/habitacion',component:MantenimientoHabitacionComponent,children:[
      {path:"registrar",component:RegistrarHabitacionComponent},
      {path:"listar",component:ListarHabitacionComponent}
    ]},
    {path:'reporte/reservacion',component:ReporteReservacionComponent},
    {path:'reporte/venta',component:ReporteVentaComponent},
    {path:'servicio/reservacion',component:ServicioReservacionComponent},
    {path:'servicio/venta',component:ServicioVentaComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
