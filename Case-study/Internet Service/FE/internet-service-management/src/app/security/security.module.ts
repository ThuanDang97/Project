import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { HomeComponent } from './components/home/home.component';
import {ReactiveFormsModule} from "@angular/forms";
import {SecurityRoutingModule} from "./security-routing.module";



@NgModule({
  declarations: [
    LoginComponent,
    AdminComponent,
    HomeComponent,
  ],
  exports: [
    LoginComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SecurityRoutingModule
  ]
})
export class SecurityModule { }
