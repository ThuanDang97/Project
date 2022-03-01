import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ServiceListComponent} from './service-list/service-list.component';
import {ServiceDeleteComponent} from './service-delete/service-delete.component';
import {MatIconModule} from "@angular/material/icon";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
import {MatToolbarModule} from "@angular/material/toolbar";
import {ServiceRoutingModule} from "./service-routing.module";
import {AppComponent} from "../app.component";
import {NgxPaginationModule} from "ngx-pagination";
import {Ng2SearchPipeModule} from "ng2-search-filter";
import {DeleteAllComponent} from './delete-all/delete-all.component';
import {ServiceComponent} from "./service.component";

@NgModule({
  declarations: [
    ServiceListComponent,
    ServiceDeleteComponent,
    DeleteAllComponent,
    ServiceComponent
  ],
  exports: [
    ServiceListComponent,
  ],
  imports: [
    CommonModule,
    MatIconModule,
    RouterModule,
    FormsModule,
    MatDialogModule,
    MatToolbarModule,
    ServiceRoutingModule,
    NgxPaginationModule,
    Ng2SearchPipeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class ServiceModule {

}
