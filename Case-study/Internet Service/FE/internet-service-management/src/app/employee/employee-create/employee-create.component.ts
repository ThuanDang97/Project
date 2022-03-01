import { Component, OnInit } from '@angular/core';
import {FileUpload} from "../../interface/FileUpload";
import {EmployeeService} from "../../services/employee.service";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {formatDate} from "@angular/common";
import {validAgeValidators} from "../../share/checkAge.validation";
import {PositionService} from "../../services/position.service";
import {IProvince} from "../../interface/IProvince";
import {IDistrict} from "../../interface/IDistrict";
import {IWard} from "../../interface/IWard";
import {AddressService} from "../../services/address.service";
import {validConfirmPassword} from "../../share/ConfirmPassword.validator";
import {AlertService} from "../AlertService";

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  selectedFiles?: FileList;
  currentFileUpload?: FileUpload;
  positionList: any;
  percentage = 0;
  employeeForm!: FormGroup;
  msgCode= '';
  msgDateOfBirth = '';
  msgStartWorkDate = '';
  msgEmail = '';
  msgPassword = '';
  msgConfirmPass!: string;
  msgImage = '';
  isImage = false;
  avtUrl!: string;
  provinces: IProvince[] = [];
  districts: IDistrict[] = [];
  wards: IWard[] = [];
  temp: string = "";
  address: string = "";




  constructor(private employeeService : EmployeeService,
              private positionService: PositionService,
              private addressService: AddressService,
              private alertService: AlertService,
              private router: Router) { }

  ngOnInit(): void {
    this.employeeForm = new FormGroup({
      employeeId: new FormControl('', [Validators.required, Validators.pattern('^NV-\\d{4}$')]),
      fullName: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10), Validators.pattern('^[a-zA-Z\'-\'\\sáàảãạăâắằấầặẵẫậéèẻ ẽẹếềểễệóêòỏõọôốồổỗộ ơớờởỡợíìỉĩịđùúủũụưứ� �ửữựÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠ ƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼ� ��ỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞ ỠỢỤỨỪỬỮỰỲỴÝỶỸửữựỵ ỷỹ]*$')]),
      // positionId: new FormControl(),
      position: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(20)]),
      dateOfBirth: new FormControl('', [Validators.required, validAgeValidators(18,35)]),
      startWorkDate: new FormControl('', [Validators.required, this.checkStartWorkDate]),
      phone: new FormControl('', [Validators.required, Validators.pattern(/^09[0-9]{9}$/)]),
      level: new FormControl('', [Validators.required, this.checkLevel, Validators.maxLength(50)]),
      yearOfExp: new FormControl('', [Validators.required, this.checkYearOfExp, Validators.minLength(0),Validators.maxLength(100)]),
      address: new FormGroup({
        province: new FormControl(''),
        district: new FormControl(''),
        ward: new FormControl('')
      }),
      avtUrl: new FormControl(''),
      userName: new FormControl('',[Validators.required]),
      password: new FormControl('', [Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+~])[A-Za-z\\d!@#$%^&*()_+~]{6,}')]),
      confirmPassword: new FormControl('')
    },{validators:[validConfirmPassword("password","confirmPassword")]});

    this.getAllProvince();
    this.positionService.getPositionList().subscribe(data => {
      this.positionList = data;
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

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      this.selectedFiles = undefined;
      if (file) {
        this.currentFileUpload = new FileUpload(file);
        let temp = new FileUpload(file);

        this.employeeService.pushFileToStorage(this.currentFileUpload).subscribe(
          url => {
            this.avtUrl = url;
          },
          error => {
            this.employeeService.pushFileToStorage(temp).subscribe(
              url => {
                this.avtUrl = url;
              },
              error => {
                console.log('error');
              }
            );
          }
        );
      }
    }
  }

  createEmployee() {
    const formValue = this.employeeForm.value;
    if (formValue.address.ward == " " && formValue.address.district == " " && formValue.address.province == " ") {
      this.address = "";
    } else {
      if (formValue.address.ward == " " && formValue.address.district == " " && formValue.address.province != " ") {
        this.address = formValue.address.province;
      }
      if (formValue.address.ward == " " && formValue.address.district != " " && formValue.address.province != " ") {
        this.address = formValue.address.district + ', ' + formValue.address.province;
      } else {
        this.address = formValue.address.province + ', ' + formValue.address.district + ', ' + formValue.address.ward;
      }
    }
    this.employeeForm.value.url = this.avtUrl;
    console.log(this.avtUrl);
    this.employeeService.createEmployee({
          employeeId: this.employeeForm.controls.employeeId.value + '',
          fullName: this.employeeForm.controls.fullName.value ,
          positionId: this.employeeForm.controls.position.value,
          dateOfBirth: this.employeeForm.controls.dateOfBirth.value ,
          email: this.employeeForm.controls.email.value,
          address: this.employeeForm.controls.address.value.ward + ',' + this.employeeForm.controls.address.value.district + ','
          + this.employeeForm.controls.address.value.province + '',
          phone: this.employeeForm.controls.phone.value,
          startWorkDate: this.employeeForm.controls.startWorkDate.value,
          level: this.employeeForm.controls.level.value ,
          yearOfExp: this.employeeForm.controls.yearOfExp.value ,
          userName: this.employeeForm.controls.userName.value ,
          password: this.employeeForm.controls.password.value ,
          avtUrl: this.avtUrl
    }).subscribe(data => {
      // @ts-ignore
      if (data.status === false) {
        // @ts-ignore
        this.msgEmail = data.msgEmail;
        // @ts-ignore
        this.msgPassword = data.msgPassword;
        // @ts-ignore
        this.msgCode = data.msgCode;
        // @ts-ignore
        this.msgDateOfBirth = data.msgDateOfBirth;
        // @ts-ignore
        this.msgStartWorkDate = data.msgStartWorkDate;
        this.alertService.showMessageErrors('Thêm mới thất bại');
      }else {
        this.router.navigateByUrl('/listEmployee');
        this.alertService.showAlertSuccess('Thêm mới thành công');
      }
    });
  }

  resetMsgCode() {
    this.msgCode = '';

  }

  resetMsgDateOfBirth() {
    this.msgDateOfBirth = '';
  }

  resetMsgStartWorkDate() {
    this.msgStartWorkDate = '';
  }

  resetMsgEmail() {
    this.msgEmail = '';
  }

  checkPassword(newPassword: string, confirmPassword: string) {
    if (newPassword !== confirmPassword) {
      return this.msgConfirmPass = 'Mật khẩu trùng với mật khẩu mới';
    } else {
      return this.msgConfirmPass = '';
    }
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyyhhmmssa', 'en-US');
  }
  checkStartWorkDate(data: AbstractControl): any{
    const startWorkDate = data.value;
    const currentDate = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
    if (startWorkDate < currentDate) {
      return {inValidDate: true};
    }
    return null;
  }
  checkLevel(data: AbstractControl): any {
    return (data.value > 0 && data.value < 50 ) ? null :{invalidLevel: true};
  }

  checkYearOfExp(data: AbstractControl): any {
    return (data.value >= 0 && data.value < 50) ? null : {invalidYearOfExp: true};
  }
}
