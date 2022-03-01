import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {Customer} from "../../entity/Customer";
import {TokenStorageService} from "../../service/token-storage.service";
import {Router} from "@angular/router";
import {Orders} from "../../entity/Orders";
import {LoadCssService} from "../../service/load-css.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  customerLogined!: Customer;
  role!: string;
  orders_hour!: Orders;
  startTime!: string;

  constructor(private userService: UserService, private tokenStorage: TokenStorageService,
              private router: Router,private loadCssService:LoadCssService) {
  }

  ngOnInit(): void {


    this.loadCssService.loadScript('assets/home/sidebar-05/js/main.js');
    this.customerLogined = this.tokenStorage.getUser();
    this.orders_hour = this.tokenStorage.getUser().orderHour;
    this.role = this.tokenStorage.getUser().roles;
  }


  signOut() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }

}
