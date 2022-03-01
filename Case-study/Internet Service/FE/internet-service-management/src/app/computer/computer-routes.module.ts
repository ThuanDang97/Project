import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {ShowComputerComponent} from "./show-computer/show-computer.component";
import {DeleteComputerComponent} from "./delete-computer/delete-computer.component";
import {ComputerCreateComponent} from "./computer-create/computer-create.component";
import {ComputerEditComponent} from "./computer-edit/computer-edit.component";

const routesComputer: Routes =[
  {path: 'listComputer', component: ShowComputerComponent},
  {path: 'createComputer', component: ComputerCreateComponent},
  {path: 'editComputer/:id', component: ComputerEditComponent},
  {path: 'deleteComputer/:id', component: DeleteComputerComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routesComputer)
  ]
})
export class ComputerRoutesModule { }
