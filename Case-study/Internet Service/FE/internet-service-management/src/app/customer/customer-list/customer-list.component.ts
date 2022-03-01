import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "../customer.service";
import {ToastrService} from "ngx-toastr";

interface Status {
  value: number,
  viewValue: string
}

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  Customer: any;
  statusList: any;
  searchForm!: FormGroup;
  totalPage = 0;
  page = 0;
  flagSearch: number | undefined;
  nameDelete: string | undefined;
  idDelete: number | undefined;
  // phonePipe: string;

  constructor(private customerService: CustomerService,
              private toast: ToastrService) { }


  ngOnInit(): void {
    this.searchForm = new FormGroup(
      {
        InputUsername: new FormControl(''),
        InputAddress: new FormControl(''),
        dateOfBirthFrom: new FormControl(''),
        dateOfBirthTo: new FormControl(''),
        status: new FormControl('', Validators.required)
      }, {validators: this.DateBirthSearchValidator});
    this.flagSearch = 0;
    this.getAllCustomer();
    // this.getStatusAndAddress();
  }


  status: Status[] = [
    {value: 0 , viewValue: "Block"},
    {value: 1 , viewValue: "Doing"}
  ];



  getAllCustomer() {
    this.customerService.getAllCustomer(this.page).subscribe(value => {
      console.log(value);
      this.Customer = value.content;
      this.totalPage = value.totalPages;
      console.log(this.totalPage);
    }, error => {
    });
  }


  searchCustomer() {
    this.flagSearch = 1;

    if (this.searchForm?.value.user_name === '' && this.searchForm.value.dateOfBirthFrom === '' &&
      this.searchForm.value.dateOfBirthTo === '' && this.searchForm.value.status === '' && this.searchForm.value.address === '') {
      this.toast.warning('Please enter if you want to search', 'massage search');
    } else {
      console.log(this.searchForm?.value);
      this.customerService.searchCustomer(this.page, this.searchForm?.value.account, this.searchForm?.value.status,
        this.searchForm?.value.address, this.searchForm?.value.dateOfBirthFrom,
        this.searchForm?.value.dateOfBirthTo
      ).subscribe(value => {
        this.totalPage = value.totalPages;
        this.Customer = value.content;
        console.log(value.content);
        console.log(this.totalPage);
      }, error => {
        this.toast.error('not found customer', 'message search', {
          timeOut: 5000
        });
        this.flagSearch = 0;
      });
    }
  }


  setPage() {
    if (this.flagSearch == 1) {
      this.page = 0;
    }
  }

  firstPage() {
    this.page = 0;
    this.getAllCustomer();
  }


  previousPage() {
    if (this.page > 0) {
      this.page--;
    } else {
      this.page = 0;
    }
    console.log(this.page);
    if (this.flagSearch == 0) {
      this.getAllCustomer();
    } else {
      this.searchCustomer();
    }
  }

  nextPage() {
    if (this.page < this.totalPage - 1) {
      this.page++;
    } else {
      this.page = this.totalPage - 1;
    }
    console.log(this.page);
    if (this.flagSearch == 0) {
      this.getAllCustomer();
    } else {
      this.searchCustomer();
    }
  }

  lastPage() {
    this.page = this.totalPage - 1;
    this.getAllCustomer();

  }

  toPage(page: number) {
    if (page < this.totalPage && page >= 0) {
      this.page = page;
      if (this.flagSearch == 0) {
        console.log(page);
        this.getAllCustomer();
      } else {
        this.searchCustomer();
      }
    } else {
      this.toast.warning('Request to enter the number of pages in the list', 'message search page');
      if (this.flagSearch == 0) {
        console.log(page);
        this.getAllCustomer();
      } else {
        this.searchCustomer();
      }
    }
  }

  showDelete(name: string, id: number) {
    this.nameDelete = name;
    this.idDelete = id;
    console.log(this.nameDelete, this.idDelete);
  }

  deleteCustomer() {
    this.customerService.deleteCustomer(this.idDelete).subscribe(value => {
        this.reset();
        // this.ngOnInit();
        this.toast.success('delete ' + this.nameDelete + ' success', 'massage delete');
      },
      error => {
        this.toast.info('delete ' + this.nameDelete + 'failure', 'massage delete');
      });
  }

  reset() {
    this.searchForm = new FormGroup(
      {
        username: new FormControl(''),
        address: new FormControl(''),
        dateOfBirthFrom: new FormControl(''),
        dateOfBirthTo: new FormControl(''),
        status: new FormControl('')
      }
    );
    this.flagSearch = 0;
    this.page = 0;
    this.getAllCustomer();
  }
}
