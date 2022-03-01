import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ComputerCreateComponent} from "./computer-create/computer-create.component";
import {ComputerEditComponent} from "./computer-edit/computer-edit.component";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {NgxPaginationModule} from "ngx-pagination";
import {ShowComputerComponent} from "./show-computer/show-computer.component";
import {DeleteComputerComponent} from "./delete-computer/delete-computer.component";
import {MatDialogActions, MatDialogModule} from "@angular/material/dialog";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    ComputerCreateComponent,
    ComputerEditComponent,
    ShowComputerComponent,
    DeleteComputerComponent
  ],
  exports: [
    ComputerCreateComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    MatDialogModule,
    CommonModule,
    RouterModule
  ]
})
export class ComputerModule { }
