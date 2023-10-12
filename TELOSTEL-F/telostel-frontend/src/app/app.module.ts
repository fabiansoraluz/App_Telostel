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
import { FormsModule } from '@angular/forms';
import { SystemComponent } from './components/system/system.component';
import { AsideComponent } from './components/aside/aside.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrarComponent,
    RecuperarComponent,
    DashboardComponent,
    RRComponent,
    SystemComponent,
    AsideComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    UtilesService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
