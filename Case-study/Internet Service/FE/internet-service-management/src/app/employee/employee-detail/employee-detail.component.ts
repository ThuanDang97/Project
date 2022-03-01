import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeeService} from "../../services/employee.service";
import {PositionService} from "../../services/position.service";
import {AddressService} from "../../services/address.service";
import {IProvince} from "../../interface/IProvince";
import {IDistrict} from "../../interface/IDistrict";
import {IWard} from "../../interface/IWard";
import {IEmployeeCreate} from "../../interface/IEmployeeCreate";


@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.css']
})
export class EmployeeDetailComponent implements OnInit {


  positionList: any;
  provinces: IProvince[] = [];
  districts: IDistrict[] = [];
  wards: IWard[] = [];
  temp: string = "";
  province: IProvince = {};
  id!: string;
  viewEmployeeForm!: FormGroup;


  constructor(private employeeService : EmployeeService,
              private positionService: PositionService,
              private addressService: AddressService,
              private activatedRoute: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllProvince();
    this.getPositionList();
    this.viewEmployeeForm = new FormGroup({
      employeeId: new FormControl(),
      fullName: new FormControl(),
      position: new FormControl(),
      email: new FormControl(),
      dateOfBirth: new FormControl(),
      startWorkDate: new FormControl(),
      phone: new FormControl(),
      level: new FormControl(),
      yearOfExp: new FormControl(),
      address: new FormGroup({
        province: new FormControl(''),
        district: new FormControl(''),
        ward: new FormControl('')
      }),

      avtUrl: new FormControl(''),
      userName: new FormControl(),
      password: new FormControl(),
      confirmPassword: new FormControl('')
    });
    this.activatedRoute.paramMap.subscribe((paramMap) =>{
      this.employeeService.getEmployeeById(this.id).subscribe((data =>{
        let addressArr = data.address.split(',');
        this.getAllDistrict(addressArr[2]);
        this.getAllWard(addressArr[1]);
        this.viewEmployeeForm.patchValue({
          employeeId: data.employeeId,
          fullName: data.fullName,
          position: data.positionId,
          dateOfBirth: data.dateOfBirth,
          email: data.email,
          address: {
            ward: addressArr[0],
            district: addressArr[1],
            province: addressArr[2],
          },
          phone: data.phone,
          startWorkDate: data.startWorkDate,
          level: data.level,
          yearOfExp: data.yearOfExp,
          userName: data.userName,
          password: data.password,
          avtUrl: data.avtUrl
        });
      }))
    });
  }

  getAllProvince(){
    this.addressService.getAllProvince().subscribe(data =>{
        this.provinces = data.results;
      },
      error => {
        console.log("can not province");
      }
    )
  }
  getAllDistrict(province: string){
    this.temp = province.split("&")[1];
    this.addressService.getAllDistrict(this.temp).subscribe(data =>{
        this.districts = data.results;
        this.wards = [];
      },
      error => {
        console.log("can not district");
      }
    )
  }

  getAllWard(district: string){
    this.temp = district.split("&")[1];
    this.addressService.getAllWard(this.temp).subscribe(data =>{
        this.wards = data.results;
      },
      error => {
        console.log("can not district");
      })
  }
  private getPositionList() {
    this.positionService.getPositionList().subscribe(data =>{
      this.positionList = data;
    })
  }


}
