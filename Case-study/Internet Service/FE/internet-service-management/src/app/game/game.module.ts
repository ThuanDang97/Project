import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GamePopularComponent } from './game-popular/game-popular.component';
import { GameDeleteComponent } from './game-delete/game-delete.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    GamePopularComponent,
    GameDeleteComponent
  ],
    imports: [
        CommonModule,
        HttpClientModule,
        RouterModule,
        FormsModule
    ]
})
export class GameModule { }
