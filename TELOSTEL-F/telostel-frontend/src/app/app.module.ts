import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrarComponent } from './components/rr/registrar/registrar.component';
import { RecuperarComponent } from './components/rr/recuperar/recuperar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RRComponent } from './components/rr/rr.component';
import { UtilesService } from './services/utiles.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SystemComponent } from './components/system/system.component';
import { AsideComponent } from './components/aside/aside.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { ServicioVentaComponent } from './servicio/servicio-venta/servicio-venta.component';
import { ServicioReservacionComponent } from './servicio/servicio-reservacion/servicio-reservacion.component';
import { ConsultaProductoComponent } from './consulta/consulta-producto/consulta-producto.component';
import { ConsultaReservacionComponent } from './consulta/consulta-reservacion/consulta-reservacion.component';
import { ConsultaClienteComponent } from './consulta/consulta-cliente/consulta-cliente.component';
import { ReporteVentaComponent } from './reporte/reporte-venta/reporte-venta.component';
import { ReporteReservacionComponent } from './reporte/reporte-reservacion/reporte-reservacion.component';
import { errorTailorImports, provideErrorTailorConfig } from '@ngneat/error-tailor';
import { MantenimientoProductoComponent } from './mantenimiento/mantenimiento-producto/mantenimiento-producto.component';
import { MantenimientoHabitacionComponent } from './mantenimiento/mantenimiento-habitacion/mantenimiento-habitacion.component';
import { ProductoService } from './services/producto.service';
import { interceptorProvider } from './interceptor/token.interceptor';
import { MantenimientoClienteComponent } from './mantenimiento/mantenimiento-cliente/mantenimiento-cliente.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RecuperarComponent,
    DashboardComponent,
    RRComponent,
    SystemComponent,
    AsideComponent,
    PerfilComponent,
    ServicioVentaComponent,
    ServicioReservacionComponent,
    ConsultaProductoComponent,
    ConsultaReservacionComponent,
    ConsultaClienteComponent,
    ReporteVentaComponent,
    ReporteReservacionComponent,
    RegistrarComponent,
    MantenimientoProductoComponent,
    MantenimientoHabitacionComponent,
    MantenimientoClienteComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    errorTailorImports,
  ],
  providers: [
    ProductoService,
    UtilesService,
    interceptorProvider,
    provideErrorTailorConfig({
      errors: {
        useValue: {
          required: 'Campo obligatorio',
          minlength: ({ requiredLength, actualLength }) => 
                      `Se espera ${requiredLength} caracteres pero tienes ${actualLength}`,
          maxlength: ({ requiredLength, actualLength }) => 
          `Se espera ${requiredLength} caracteres pero tienes ${actualLength}`,
          pattern:'Debes seguir el formato',
          invalidAddress: error => `Address isn't valid`,
          email:'Ingresar un formato de correo válido',
          dateInvalid:'Ingresar una fecha valida'
        }
      }
    })
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
