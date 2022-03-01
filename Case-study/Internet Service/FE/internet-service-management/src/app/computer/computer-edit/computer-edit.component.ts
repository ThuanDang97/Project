import {Component, Inject, OnInit} from '@angular/core';
import {ComputerService} from "../../services/computer.service";
import {ManufacturerService} from "../../services/manufacturer.service";
import {StatusService} from "../../services/status.service";
import {TypeService} from "../../services/type.service";
import {ActivatedRoute, Router} from "@angular/router";
import {IManufacturer} from "../../interface/IManufacturer";
import {IStatus} from "../../interface/IStatus";
import {IType} from "../../interface/IType";
import {IComputer} from "../../interface/IComputer";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-computer-edit',
  templateUrl: './computer-edit.component.html',
  styleUrls: ['./computer-edit.component.css']
})
export class ComputerEditComponent implements OnInit {


  constructor(private _computerService: ComputerService, private _manufacturerService: ManufacturerService,
              private _statusService: StatusService, private _typeService: TypeService, private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  listManufacturer!: IManufacturer[] ;
  listStatus!: IStatus[];
  listType!: IType[];
  id: string = '';

  updateForm = new FormGroup({
      computerId: new FormControl('', [Validators.required, Validators.pattern("^CP-{1}[\\d]{4}$")]),
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
    return this.updateForm.get("computerId");
  }

  getComputerIpLocal() {
    return this.updateForm.get("computerIpLocal");
  }

  getComputerLocation(){
    return this.updateForm.get("computerLocation");
  }

  getComputerStartUsedDate(){
    return this.updateForm.get("computerStartUsedDate");
  }

  getComputerWarrantyPeriod(){
    return this.updateForm.get("computerWarrantyPeriod");
  }

  getComputerConfiguration(){
    return this.updateForm.get("computerConfiguration");
  }

  getManufacturer(){
    return this.updateForm.get("manufacturer");
  }

  getStatus(){
    return this.updateForm.get("status");
  }

  getType(){
    return this.updateForm.get("type");
  }

  ngOnInit(): void {

    this._manufacturerService.getAllManufacturer().subscribe((data) => {
      // @ts-ignore
      this.listManufacturer = data;
    });

    this._statusService.getAllStatus().subscribe((data) => {
      // @ts-ignore
      this.listStatus = data;
    });

    this._typeService.getAllType().subscribe((data) => {
      // @ts-ignore
      this.listType = data;
    });

    this.activatedRoute.paramMap.subscribe((param) => {
      // @ts-ignore
      this.id = param.get("id");
      this._computerService.findById(this.id).subscribe((data: IComputer) => {
        this.updateForm.patchValue(data);
      })
    })
  }

  updateComputer() {
    this._computerService.update(this.id, this.updateForm.value).subscribe(data => {
      return this.router.navigateByUrl("");
    })
  }

  // @ts-ignore
  compareWith(val1, val2) {
    return val1.id === val2.id;
  }
}
