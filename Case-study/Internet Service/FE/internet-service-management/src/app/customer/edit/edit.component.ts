import { Component, OnInit } from '@angular/core';
import {Customer} from "../Customer";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "../customer.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Province} from "../Province";
import {District} from "../District";
import {Commune} from "../Commune";
import {AddressSelectService} from "../address-select.service";
import {ageValidator, passwordConfirm} from "../customer.validator";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  customerId: number;
  customer: Customer;
  form: FormGroup;
  provinces: Province[] = [];
  districts: District[] = [];
  communes: Commune[] = [];
  editSuccess: boolean = false;
  selectedProvinceName: string;
  selectedDistrictName: string;
  selectedCommuneName: string;

  selectedProvinceId: string;
  selectedDistrictId: string;
  selectedCommuneId: string;
  temp: string = "";


  constructor(public customerService: CustomerService,
              public addressSelectService: AddressSelectService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.form = new FormGroup({
        customerId: new FormControl('', [Validators.required]),
        fullName: new FormControl('', [Validators.required, Validators.pattern("^([a-zA-Z ])*$")]),
        province: new FormControl(''),
        district: new FormControl(''),
        commune: new FormControl(''),
        email: new FormControl('', [Validators.required,
          Validators.pattern("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")]),
        phone: new FormControl('', [Validators.required, Validators.pattern("^([0-9]{10}|[0-9]{12})$")]),
        dateOfBirth: new FormControl('', [Validators.required, ageValidator(16)]),
        status: new FormControl('', [Validators.required]),
        username: new FormControl('', [Validators.required]),
        password: new FormControl('', [Validators.required,
          Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")]),
        passwordRetype: new FormControl('', [Validators.required])
      },
      {
        validators: [passwordConfirm("password", "passwordRetype")]
      }
    );
      this.getAllProvince();
      this.customerId = this.route.snapshot.params['id'];
      this.customerService.getById(this.customerId).subscribe((data: Customer) => {
          this.customer = data;
        this.form = new FormGroup({
          customerId: new FormControl(this.customer.customerId),
            fullName: new FormControl(this.customer.fullName, [Validators.required]),
            province: new FormControl(this.customer.province),
            district: new FormControl(this.customer.district),
            commune: new FormControl(this.customer.commune),
            email: new FormControl(this.customer.email, [Validators.required,
              Validators.pattern("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")]),
            phone: new FormControl(this.customer.phone, [Validators.required, Validators.pattern("^([0-9]{10}|[0-9]{12})$")]),
            dateOfBirth: new FormControl(this.customer.dateOfBirth, [Validators.required, ageValidator(16)]),
            status: new FormControl(this.customer.status, [Validators.required]),
            username: new FormControl(this.customer.username, [Validators.required]),
            password: new FormControl(this.customer.password, [Validators.required,
              Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")]),
            passwordRetype: new FormControl(this.customer.password, [Validators.required])
          },
          {
            validators: [passwordConfirm("password", "passwordRetype")]
          }
        );
      });
    //   this.selectedProvinceName = this.customer?.province?.split('&')[0];
    //   this.selectedDistrictName = this.customer?.district?.split('&')[0];
    //   this.selectedCommuneName = this.customer?.commune?.split('&')[0];
    // this.selectedProvinceId = this.customer?.province?.split('&')[1];
    // this.selectedDistrictId = this.customer?.district?.split('&')[1];
    // this.selectedCommuneId = this.customer?.commune?.split('&')[1];
    // this.getAllDistrict(this.customer.province);
    // this.getAllWard(this.customer.district);
  }

  getAllProvince(){
    this.addressSelectService.getAllProvince().subscribe(data =>{
        this.provinces = data.results;
      },
      error => {
        console.log("Cant get provinces");
      }
    )
  }
  getAllDistrict(province: string){
    this.temp = province.split("&")[1];
    this.addressSelectService.getAllDistrict(this.temp).subscribe(data =>{
        this.districts = data.results;
        this.communes = [];
      },
      error => {
        console.log("Cant get districts");
      }
    )
  }

  getAllWard(district: string){
    this.temp = district.split("&")[1];
    this.addressSelectService.getAllCommune(this.temp).subscribe(data =>{
        this.communes = data.results;
      },
      error => {
        console.log("Cant get communes");
      })
  }


  get f(){
    return this.form.controls;
  }

  compareFn(c1: Customer, c2: Customer): boolean {
    return c1 && c2 ? c1.customerId === c2.customerId : c1 === c2;
  }

  fadeOutLink() {
    setTimeout( () => {
      this.editSuccess = false;
    }, 2000);
  }

  submit(){
    if (this.form.valid) {
      this.customerService.edit(this.customerId, this.form.value).subscribe(res => {
        this.editSuccess = true;
        this.fadeOutLink();      })
    }
  }
}
