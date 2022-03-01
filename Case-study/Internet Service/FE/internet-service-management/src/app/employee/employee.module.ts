import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {EmployeeCreateComponent} from "./employee-create/employee-create.component";
import {EmployeeDetailComponent} from "./employee-detail/employee-detail.component";
import {EmployeeEditComponent} from "./employee-edit/employee-edit.component";
import {ReactiveFormsModule} from "@angular/forms";
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFireDatabaseModule } from '@angular/fire/compat/database';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { environment } from 'src/environments/environment';
import {ListEmployeeComponent} from "./list-employee/list-employee.component";
import {DeleteEmployeeComponent} from "./delete-employee/delete-employee.component";
import {NgxPaginationModule} from "ngx-pagination";
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {PhonePipe} from "./phone.pipe";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    EmployeeCreateComponent,
    EmployeeDetailComponent,
    EmployeeEditComponent,
    ListEmployeeComponent,
    DeleteEmployeeComponent,
    PhonePipe
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireDatabaseModule,
    AngularFireStorageModule,
    NgxPaginationModule,
    MatDialogModule,
    MatButtonModule,
    RouterModule
  ]
})
export class EmployeeModule { }
