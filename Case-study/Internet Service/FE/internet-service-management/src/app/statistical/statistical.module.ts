import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ViewMainComponent } from './view-main/view-main.component';
import {ChartsModule} from "ng2-charts";



@NgModule({
    declarations: [
        ViewMainComponent,
    ],
    exports: [
        ViewMainComponent
    ],
    imports: [
      CommonModule,
      ChartsModule
    ]
})
export class StatisticalModule { }
