import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {ListEmployeeComponent} from "./list-employee/list-employee.component";
import {EmployeeCreateComponent} from "./employee-create/employee-create.component";
import {EmployeeDetailComponent} from "./employee-detail/employee-detail.component";


const routersEmployee: Routes =[
  {path: 'listEmployee',component: ListEmployeeComponent},
  {path: 'createEmployee',component: EmployeeCreateComponent},
  {path: 'viewEmployee:/id',component: EmployeeDetailComponent},
];
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routersEmployee)
  ]
})
export class EmployeeRouterModule { }
