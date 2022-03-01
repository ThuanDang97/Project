import {Component, Inject, OnInit} from '@angular/core';
import {ComputerService} from "../../services/computer.service";
import {ManufacturerService} from "../../services/manufacturer.service";
import {StatusService} from "../../services/status.service";
import {TypeService} from "../../services/type.service";
import {Router} from "@angular/router";
import {IManufacturer} from "../../interface/IManufacturer";
import {IStatus} from "../../interface/IStatus";
import {IType} from "../../interface/IType";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-computer-create',
  templateUrl: './computer-create.component.html',
  styleUrls: ['./computer-create.component.css']
})
export class ComputerCreateComponent implements OnInit {

  constructor(private _computerService: ComputerService, private _manufacturerService: ManufacturerService,
              private _statusService: StatusService, private _typeService: TypeService, private router: Router) {
  }

  public listManufacturer!: IManufacturer[];
  public listStatus!: IStatus[];
  public listType!: IType[];

  createForm = new FormGroup({
      computerId: new FormControl('', [Validators.required, Validators.pattern("^CP-[\\d]{4}$")]),
      computerLocation: new FormControl('', [Validators.required, Validators.pattern('^[A-Z]{1}[\\d]{4}$')]),
      computerStartUsedDate: new FormControl('', [Validators.required, Validators.pattern('')]),
      computerWarrantyPeriod: new FormControl('', [Validators.required]),
      computerConfiguration: new FormControl('', [Validators.required]),
      computerIpLocal: new FormControl('', [Validators.required]),
      manufacturer: new FormControl('', [Validators.required]),
      status: new FormControl('', [Validators.required]),
      type: new FormControl('', [Validators.required]),
    }
  );


  getID() {
    return this.createForm.get("computerId");
  }

  getComputerLocation() {
    return this.createForm.get("computerLocation");
  }

  getComputerIpLocal() {
    return this.createForm.get("computerIpLocal");
  }

  getComputerStartUsedDate() {
    return this.createForm.get("computerStartUsedDate");
  }

  getComputerWarrantyPeriod() {
    return this.createForm.get("computerWarrantyPeriod");
  }

  getComputerConfiguration() {
    return this.createForm.get("computerConfiguration");
  }

  getManufacturer() {
    return this.createForm.get("manufacturer");
  }

  getStatus() {
    return this.createForm.get("status");
  }

  getType() {
    return this.createForm.get("type");
  }

  createComputer() {
    this._computerService.create(this.createForm.value).subscribe();
    this.router.navigateByUrl('');
  }

  ngOnInit(): void {

    this._manufacturerService.getAllManufacturer().subscribe(data => {
      this.listManufacturer = data;
      console.log(data)
    });

    this._statusService.getAllStatus().subscribe(data => {
      this.listStatus = data;
      console.log(data)

    });

    this._typeService.getAllType().subscribe(data => {
      this.listType = data;
      console.log(data)
    });
  }

}
