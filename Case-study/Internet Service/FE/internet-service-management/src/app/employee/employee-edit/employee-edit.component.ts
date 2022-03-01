import {Component, OnInit} from '@angular/core';
import {FileUpload} from "../../interface/FileUpload";
import {IPosition} from "../../interface/IPosition";
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../services/employee.service";
import {PositionService} from "../../services/position.service";
import {ActivatedRoute, Router} from "@angular/router";
import {IProvince} from "../../interface/IProvince";
import {IDistrict} from "../../interface/IDistrict";
import {IWard} from "../../interface/IWard";
import {AddressService} from "../../services/address.service";
import {validAgeValidators} from "../../share/checkAge.validation";
import {formatDate} from "@angular/common";
import {validConfirmPassword} from "../../share/ConfirmPassword.validator";
import {ToastrService} from "ngx-toastr";
import {Employee} from "../../interface/Employee";

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {

  selectedFiles?: FileList;
  currentFileUpload?: FileUpload;
  positionList: IPosition[];
  msgCode = '';
  msgDateOfBirth = '';
  msgStartWorkDate = '';
  msgEmail = '';
  msgPassword = '';
  msgConfirmPass!: string;
  msgImage = '';
  isImage = false;
  avtUrl!: string;
  address: string = "";
  provinces: IProvince[] = [];
  districts: IDistrict[] = [];
  wards: IWard[] = [];
  temp: string = "";
  province: IProvince = {};
  employeeEditForm!: FormGroup;
  id!: string;
  employee!: Employee;


  constructor(private employeeService: EmployeeService,
              private positionService: PositionService,
              private addressService: AddressService,
              private activatedRoute: ActivatedRoute,
              public toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllProvince();
    this.getPositionList();
    // this.activatedRoute.paramMap.subscribe(param =>{
    //   this.id = <string>param.get('id');
    this.employeeEditForm = new FormGroup({
      employeeId: new FormControl('', [Validators.required, Validators.pattern('^NV-\\d{4}$')]),
      fullName: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10), Validators.pattern('^[a-zA-Z\'-\'\\sáàảãạăâắằấầặẵẫậéèẻ ẽẹếềểễệóêòỏõọôốồổỗộ ơớờởỡợíìỉĩịđùúủũụưứ� �ửữựÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠ ƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼ� ��ỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞ ỠỢỤỨỪỬỮỰỲỴÝỶỸửữựỵ ỷỹ]*$')]),
      position: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(20)]),
      dateOfBirth: new FormControl('', [Validators.required, validAgeValidators(18, 35)]),
      startWorkDate: new FormControl('', [Validators.required, this.checkStartWorkDate]),
      phone: new FormControl('', [Validators.required, Validators.pattern(/^09[0-9]{9}$/)]),
      level: new FormControl('', [Validators.required, this.checkLevel, Validators.maxLength(50)]),
      yearOfExp: new FormControl('', [Validators.required, this.checkYearOfExp, Validators.minLength(0), Validators.maxLength(100)]),
      address: new FormGroup({
        province: new FormControl(''),
        district: new FormControl(''),
        ward: new FormControl('')
      }),
      avtUrl: new FormControl(''),
      userName: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+~])[A-Za-z\\d!@#$%^&*()_+~]{6,}')]),
      confirmPassword: new FormControl('')
    }, {
      validators: [validConfirmPassword("password", "confirmPassword")]
    });
    this.employeeService.getEmployeeById(this.id).subscribe(data => {
      // console.log(data);
      let addressArr = data.address.split(',');
      this.getAllDistrict(addressArr[2]);
      this.getAllWard(addressArr[1]);
      this.employeeEditForm.patchValue({
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
        avtUrl: data.avtUrl,
      });
      this.avtUrl = data.avtUrl;
      // console.log(this.employeeEditForm)

    });

  }

  editEmployee() {

    const formValue = this.employeeEditForm.value;
    if (formValue.address.ward == " " && formValue.address.district == " " && formValue.address.province == " ") {
      this.address = "";
    } else {
      if (formValue.address.ward == " " && formValue.address.district == " " && formValue.address.province != " ") {
        this.address = formValue.address.province;
      }
      if (formValue.address.ward == " " && formValue.address.district != " " && formValue.address.province != " ") {
        this.address = formValue.address.district + ',' + formValue.address.province;
      } else {
        this.address = formValue.address.ward + ',' + formValue.address.district + ',' + formValue.address.province;
      }
    }
    this.employee = new Employee(formValue.employeeId, formValue.fullName, formValue.dateOfBirth, formValue.email, this.address,
      formValue.phone, formValue.level, formValue.startWorkDate, formValue.yearOfExp, this.avtUrl,
      formValue.position, formValue.userName, formValue.password);
    // console.log(this.employee);
    this.employeeService.editEmployee(this.employeeEditForm.value.employeeId, this.employee).subscribe(data => {
        this.router.navigateByUrl('listEmployee');

      }, error => {
        console.log(error)
      }
    );

  }

  getAllProvince() {
    this.addressService.getAllProvince().subscribe(data => {
        this.provinces = data.results;
      },
      error => {
        console.log("can not province");
      }
    )
  }

  getAllDistrict(province: string) {
    this.temp = province.split("&")[1];
    this.addressService.getAllDistrict(this.temp).subscribe(data => {
        this.districts = data.results;
        this.wards = [];
      },
      error => {
        console.log("can not district");
      }
    )
  }

  getAllWard(district: string) {
    this.temp = district.split("&")[1];
    this.addressService.getAllWard(this.temp).subscribe(data => {
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

  checkStartWorkDate(data: AbstractControl): any {
    const startWorkDate = data.value;
    const currentDate = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
    if (startWorkDate < currentDate) {
      return {inValidDate: true};
    }
    return null;
  }

  checkLevel(data: AbstractControl): any {
    return (data.value > 0 && data.value < 50) ? null : {invalidLevel: true};
  }

  checkYearOfExp(data: AbstractControl): any {
    return (data.value >= 0 && data.value < 50) ? null : {invalidYearOfExp: true};
  }

  compareP(val1: any, val2: any): boolean {
    return val1 && val2 ? val1.positionId === val2.positionId : val1 === val2;
  }

  private getPositionList() {
    this.positionService.getPositionList().subscribe(data => {
      this.positionList = data;
    })
  }
}
