import {Component, Inject, OnInit} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {ServiceService} from "../../services/service.service";
import {AlertService} from "../alert.service";

@Component({
  selector: 'app-service-delete',
  templateUrl: './service-delete.component.html',
  styleUrls: ['./service-delete.component.css']
})
export class ServiceDeleteComponent implements OnInit {

  public deleteServicesID: any;
  // @ts-ignore
  public servicesOfID;
  constructor(
    private dialogRef: MatDialogRef<ServiceDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public servicesService: ServiceService,
    private alertService: AlertService) {}

  ngOnInit(){
    this.deleteServicesID = this.data.data1.serviceId;
    this.servicesOfID = this.data.data1.serviceId;
  }

  deleteServices() {
    this.servicesService.deleteServicesByID(this.servicesOfID).subscribe(data =>{
      this.dialogRef.close();
      this.alertService.showAlertSuccess("Xoá dịch vụ thành công!");
    });
  }

}
