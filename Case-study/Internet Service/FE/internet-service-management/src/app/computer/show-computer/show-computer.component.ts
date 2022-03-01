import {Component, OnInit} from '@angular/core';
import {IComputer} from "../../interface/IComputer";
import {ComputerService} from "../../services/computer.service";
import {DeleteComputerComponent} from "../delete-computer/delete-computer.component";
import {MatDialog} from "@angular/material/dialog";
import {ComputerTypeService} from "../../services/computer-type.service";
import {ComputerStatusService} from "../../services/computer-status.service";
import {IType} from "../../interface/IType";
import {IStatus} from "../../interface/IStatus";
import {FormControl, FormGroup} from "@angular/forms";
import {AlertService} from "../alert.service";


@Component({
  selector: 'app-show-computer',
  templateUrl: './show-computer.component.html',
  styleUrls: ['./show-computer.component.css']
})

export class ShowComputerComponent implements OnInit {
  listComputer: IComputer[] = [];
  listComputerType: IType[] = [];
  listComputerStatus: IStatus[] = [];
  searchComputer!: FormGroup;
  indexPagination: number = 1;
  totalPagination: number = 0;

  constructor(private computerService: ComputerService, private dialog: MatDialog, private computerTypeService: ComputerTypeService,
              private computerStatusService: ComputerStatusService, private alertService: AlertService) {


  }

  ngOnInit(): void {

    this.getList();
    this.searchComputer = new FormGroup({
      idComputer: new FormControl(''),
      startUsedDateFromComputer: new FormControl(''),
      statusComputer: new FormControl(''),
      computerLocation: new FormControl(''),
      startUsedDateToComputer: new FormControl(''),
      typeComputer: new FormControl(''),
    })
  };

  openDialog(computerId: string) {
    this.computerService.getComputerById(computerId).subscribe((data) => {
      const dialog = this.dialog.open(DeleteComputerComponent, {
          width: '500px',
          data: data,
          disableClose: true,
          autoFocus: false
        }
      );
      dialog.afterClosed().subscribe(result => {
        this.ngOnInit();
      });
    })

  }

  search() {
    if (this.searchComputer.value.idComputer == '') {
      this.searchComputer.value.idComputer = "";
    }
    ;
    if (this.searchComputer.value.computerLocation == '') {
      this.searchComputer.value.computerLocation = "";
    }
    ;

    if (this.searchComputer.value.startUsedDateFromComputer == '') {
      this.searchComputer.value.startUsedDateFromComputer = "1000-01-01";
    }
    ;
    if (this.searchComputer.value.startUsedDateToComputer == '') {
      this.searchComputer.value.startUsedDateToComputer = "9999-12-31";
    }
    ;
    if (this.searchComputer.value.typeComputer == '') {
      this.searchComputer.value.typeComputer = "";
    }
    ;
    if (this.searchComputer.value.statusComputer == '') {
      this.searchComputer.value.statusComputer = "";
    }
    ;

    this.computerService.searchComputer(this.searchComputer.value.idComputer, this.searchComputer.value.computerLocation,
      this.searchComputer.value.startUsedDateFromComputer, this.searchComputer.value.startUsedDateToComputer,
      this.searchComputer.value.typeComputer, this.searchComputer.value.statusComputer).subscribe(
      (data) => {

        // @ts-ignore
        this.listComputer = data.content;
        console.log("Đây là search" + this.listComputer);


      },
      () => {
        this.alertService.showAlertSuccess("Không thể tìm kiếm máy như theo yêu cầu!");
      }
    )
  }

  getPage(pageNum: number) {

    // this.computerService.getPageList(pageNum).subscribe(data =>{
    //   this.listComputer = data.content;
    //   this.indexPagination = data.pageable.pageNumber + 1;
    // });
    if (this.searchComputer.value.startUsedDateFromComputer == '') {
      this.searchComputer.value.startUsedDateFromComputer = "1000-01-01";
    }
    ;
    if (this.searchComputer.value.startUsedDateToComputer == '') {
      this.searchComputer.value.startUsedDateToComputer = "9999-12-31";
    }
    ;
    this.computerService.getPageSearch(pageNum, this.searchComputer.value.idComputer, this.searchComputer.value.computerLocation,
      this.searchComputer.value.startUsedDateFromComputer, this.searchComputer.value.startUsedDateToComputer,
      this.searchComputer.value.typeComputer, this.searchComputer.value.statusComputer).subscribe(
      data => {

        this.listComputer = data.content;
        this.indexPagination = data.pageable.pageNumber + 1;
      })
  }

  getList() {
    this.computerService.getAllComputer().subscribe(
      (data) => {

        this.listComputer = data.content;
        this.totalPagination = data.totalPages;
        console.log(data);
      },
      () => {
        console.log("Error");
      },
      () => {
        console.log("Complete");
      }
    );

    this.computerTypeService.getAllType().subscribe((data) => {
      // @ts-ignore
      this.listComputerType = data;
    });
    this.computerStatusService.getAllStatus().subscribe((data) => {
      // @ts-ignore
      this.listComputerStatus = data;
      // this.listComputerStatus.unshift({statusId : 0, statusName : ''});
    })
  }

  pageRefresh() {
    this.ngOnInit();
  }

  searchEnter($event: KeyboardEvent) {
    if (this.searchComputer.value.idComputer == '') {
      this.searchComputer.value.idComputer = "";
    }
    ;
    if (this.searchComputer.value.computerLocation == '') {
      this.searchComputer.value.computerLocation = "";
    }
    ;

    if (this.searchComputer.value.startUsedDateFromComputer == '') {
      this.searchComputer.value.startUsedDateFromComputer = "1000-01-01";
    }
    ;
    if (this.searchComputer.value.startUsedDateToComputer == '') {
      this.searchComputer.value.startUsedDateToComputer = "9999-12-31";
    }
    ;
    if (this.searchComputer.value.typeComputer == '') {
      this.searchComputer.value.typeComputer = "";
    }
    ;
    if (this.searchComputer.value.statusComputer == '') {
      this.searchComputer.value.statusComputer = "";
    }
    ;

    this.computerService.searchComputer(this.searchComputer.value.idComputer, this.searchComputer.value.computerLocation,
      this.searchComputer.value.startUsedDateFromComputer, this.searchComputer.value.startUsedDateToComputer,
      this.searchComputer.value.typeComputer, this.searchComputer.value.statusComputer).subscribe((data) => {

      // @ts-ignore
      this.listComputer = data.content;
      console.log("Đây là search" + this.listComputer);

    })
  }
}
