import { Component, OnInit } from '@angular/core';
import {LoadJsService} from "../../service/load-js.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private loadjs: LoadJsService) {
    this.loadjs.loadScript('assets/js/menu_left.js');
  }

  ngOnInit(): void {
  }

}
