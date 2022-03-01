import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ServiceService} from "../../services/service.service";
import {AlertService} from "../alert.service";

@Component({
  selector: 'app-delete-all',
  templateUrl: './delete-all.component.html',
  styleUrls: ['./delete-all.component.css']
})
export class DeleteAllComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<DeleteAllComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public servicesService: ServiceService,
    private alertService: AlertService) {}

  ngOnInit(){

  }

  deleteServices() {
    this.servicesService.deleteAllServices().subscribe(data =>{
      this.dialogRef.close();
      this.alertService.showAlertSuccess("Xoá tất cả dịch vụ thành công!");
    });
  }

}
