import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ComputerService} from "../../services/computer.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AlertService} from "../alert.service";

@Component({
  selector: 'app-delete-computer',
  templateUrl: './delete-computer.component.html',
  styleUrls: ['./delete-computer.component.css']
})
export class DeleteComputerComponent implements OnInit {
  id!: string;
  constructor(private activatedRoute: ActivatedRoute, private computerService: ComputerService, private router: Router,
              private dialog: MatDialogRef<DeleteComputerComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private alertService: AlertService) { }

  ngOnInit(): void {
  this.id = this.data.computerId;
  }

  deleteComputer() {
    this.computerService.deleteComputer(this.id).subscribe(()=>{
      this.dialog.close();
      this.alertService.showAlertSuccess("Xóa máy tính thành công!");
    })
  }

  notDelete() {
    this.alertService.showAlertSuccess("Hủy bỏ thành công!");
  }
}
