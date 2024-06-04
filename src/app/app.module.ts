import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './auth.interceptor';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // Necessario per le animazioni
import { CalendarModule } from 'primeng/calendar';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component'; // Importa CalendarModule



@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CalendarModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
