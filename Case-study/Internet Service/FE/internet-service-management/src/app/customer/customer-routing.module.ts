import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EditComponent} from "./edit/edit.component";
import {CreateComponent} from "./create/create.component";

const routes: Routes = [{ path: 'customer/create', component: CreateComponent },
  { path: 'customer/edit/:id', component: EditComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
