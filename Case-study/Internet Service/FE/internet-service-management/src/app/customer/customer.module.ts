import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CustomerRegisterComponent} from "./customer-register/customer-register.component";
import {ReactiveFormsModule} from "@angular/forms";
import {CreateComponent} from "./create/create.component";
import {EditComponent} from "./edit/edit.component";
import {CustomerListComponent} from "./customer-list/customer-list.component";


@NgModule({
  declarations: [
    CustomerRegisterComponent,
    CreateComponent,
    EditComponent,
    CustomerListComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class CustomerModule { }
