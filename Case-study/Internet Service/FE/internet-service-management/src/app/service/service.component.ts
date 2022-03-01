import {
  AfterContentChecked,
  ChangeDetectorRef,
  Component,
  OnInit
} from '@angular/core';
import {ServiceService} from "../services/service.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {IService} from "../interface/IService";

@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.css']
})
export class ServiceComponent implements OnInit, AfterContentChecked {

  formGroupForAdd: FormGroup[] = [];
  formGroupForUpdate: FormGroup[] = [];
  serviceArr: IService[] = [];
  serviceArrCheck: IService[] = [];
  serviceArrForUpdate: IService[] = [];

  sttForm: number = 0;
  btnAddHidden: boolean = true;
  btnEditHidden: boolean = true;
  confirmHaveErr: boolean = true;

  constructor(
    private serviceService: ServiceService,
    private formBuilder: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.getAllService();
  }

  getAllService() {

    // data page here
    this.serviceService.getAllService().subscribe(
      (data) => {
        console.log(data.content);
      }
    );


    this.serviceService.getAllService().subscribe(
      (data) => {
        this.serviceArr = data
      },
      (err) => {
        console.error(err);
      },
      () => {
        this.serviceArrCheck = [];
        this.serviceArrForUpdate = [];
        this.serviceArr.forEach(
          (o, i, a) => {
            this.serviceArrForUpdate.push(o);
            this.serviceArrCheck.push(o);
          }
        );

        for (let i = 0; i < this.serviceArrForUpdate.length; i++) {
          if (this.formGroupForUpdate[i]) {
            continue
          }
          this.formGroupForUpdate[i] = this.formBuilder.group({
            serviceId: ['', [Validators.pattern("^SV\\d{4}$"), Validators.required]],
            serviceName: ['', [Validators.required]],
            quantity: ['', [Validators.required, Validators.min(1)]],
            unit: ['', [Validators.required]],
            prices: ['', [Validators.required, Validators.min(1000)]]
          });
        }

        this.serviceArrCheck.forEach(
          (o, i) => {
            if (o.serviceId == '') {
              this.serviceArrCheck[i] = this.serviceArr[i];
            }
          }
        );
      }
    );

  }

  btnAddService() {

    this.btnAddHidden = false;
    this.btnEditHidden = true;

    this.getAllService();

    for (let i = 0; i <= this.sttForm; i++) {
      if (this.formGroupForAdd[i]) {
        continue
      }
      this.formGroupForAdd[i] = this.formBuilder.group({
        serviceId: ['', [Validators.pattern("^SV\\d{4}$"), Validators.required]],
        serviceName: ['', [Validators.required]],
        quantity: ['', [Validators.required, Validators.min(1)]],
        unit: ['', [Validators.required]],
        prices: ['', [Validators.required, Validators.min(1000)]]
      });
    }
    this.sttForm++;
    console.log('btnAddService');
  }

  cancelAddService(event: any) {
    this.formGroupForAdd.splice(event.closest('tr').id, 1);
    this.sttForm--;

    if (this.sttForm == 0) {
      this.btnAddHidden = true;
      this.formGroupForAdd = [];
    }
    console.log('cancel');
  }

  submitAddService() {
    $('#contentModalAddOrUpdate').empty();
    this.btnAddHidden = true;
    this.btnEditHidden = true;

    if (this.formGroupForAdd.length > 0) {
      for (let i = 0; i < this.formGroupForAdd.length; i++) {

        let serviceIdTmp = this.formGroupForAdd[i].get('serviceId')?.value.toString().trim();
        this.formGroupForAdd[i].controls.serviceName.setValue(this.formGroupForAdd[i].controls.serviceName.value.toString().trim());

        this.serviceArr.push(this.formGroupForAdd[i].value);
        this.serviceArrCheck.push(this.formGroupForAdd[i].value);
        this.serviceArrForUpdate.push(this.formGroupForAdd[i].value);
        this.serviceService.addOrUpdate(this.formGroupForAdd[i], "add").subscribe(
          (data) => {
          },
          (err) => {
            // @ts-ignore
            jQuery('#contentModalAddOrUpdate').append(`<h4 class="text-danger">Add failed ${serviceIdTmp}</h4>`);
            // @ts-ignore
            jQuery('#modalAddOrUpdate').modal();
            console.log(err);
            // if there is an error, please send a notification
            this.getAllService();
          },
          () => {
            // @ts-ignore
            jQuery('#contentModalAddOrUpdate').append(`<h4 class="text-success">Add success ${serviceIdTmp}</h4>`);
            // @ts-ignore
            jQuery('#modalAddOrUpdate').modal();
            this.getAllService();
          }
        );
      }
    }
    this.formGroupForAdd = [];
    this.sttForm = 0;

    console.log('submitAddService');
  }

  btnEditService(index: any) {
    this.formGroupForAdd = [];
    this.sttForm = 0;
    this.btnAddHidden = true;
    this.btnEditHidden = false;

    this.serviceArrCheck[index] = {serviceId: '', serviceName: '', quantity: 0, unit: '', prices: 0};
    for (let i = 0; i < this.serviceArrForUpdate.length; i++) {

      if (i == index) {
        this.formGroupForUpdate[i] = this.formBuilder.group({
          serviceId: [this.serviceArrForUpdate[i].serviceId, [Validators.pattern("^SV\\d{4}$"), Validators.required]],
          serviceName: [this.serviceArrForUpdate[i].serviceName, [Validators.required]],
          quantity: [this.serviceArrForUpdate[i].quantity, [Validators.required, Validators.min(1)]],
          unit: [this.serviceArrForUpdate[i].unit, [Validators.required]],
          prices: [this.serviceArrForUpdate[i].prices, [Validators.required, Validators.min(1000)]]
        });
      }
    }

    console.log('btnEditService');
  }

  cancelEditService(index: any) {
    this.serviceArrCheck[index] = this.serviceArr[index];
    this.formGroupForUpdate[index] = this.formBuilder.group({
      serviceId: ['', [Validators.pattern("^SV\\d{4}$"), Validators.required]],
      serviceName: ['', [Validators.required]],
      quantity: ['', [Validators.required, Validators.min(1)]],
      unit: ['', [Validators.required]],
      prices: ['', [Validators.required, Validators.min(1000)]]
    });

    let countFormGroupForUpdateExist: number = 0;
    this.formGroupForUpdate.forEach(
      (o, i) => {
        if (o.get('serviceId')?.value != '') {
          countFormGroupForUpdateExist++
        }
      }
    );
    if (countFormGroupForUpdateExist == 0) {
      this.btnEditHidden = true;
    }

    console.log(this.formGroupForUpdate);
  }

  submitEditService() {

    $('#contentModalAddOrUpdate').empty();
    this.btnAddHidden = true;
    this.btnEditHidden = true;

    this.formGroupForUpdate.forEach(
      (o, i) => {
        this.serviceArrCheck[i] = {serviceId: '', serviceName: '', quantity: 0, unit: '', prices: 0};
        if (o.get('serviceId')?.value != '') {

          let serviceIdTmp = this.formGroupForUpdate[i].get('serviceId')?.value.toString().trim();
          this.formGroupForUpdate[i].controls.serviceName.setValue(this.formGroupForUpdate[i].controls.serviceName.value.toString().trim());

          this.serviceService.addOrUpdate(this.formGroupForUpdate[i], "update").subscribe(
            (data) => {
            },
            (err) => {
              // @ts-ignore
              jQuery('#contentModalAddOrUpdate').append(`<h4 class="text-danger">Edit failed ${serviceIdTmp}</h4>`);
              // @ts-ignore
              jQuery('#modalAddOrUpdate').modal();
              console.log(err);
              // if there is an error, please send a notification
              this.getAllService();
            },
            () => {
              // @ts-ignore
              jQuery('#contentModalAddOrUpdate').append(`<h4 class="text-success">Edit success ${serviceIdTmp}</h4>`);
              // @ts-ignore
              jQuery('#modalAddOrUpdate').modal();
              this.getAllService();
              this.btnAddHidden = true;
              this.btnEditHidden = true;
            }
          );

          this.serviceArr[i].serviceName = this.formGroupForUpdate[i].controls.serviceName.value;
          this.serviceArr[i].quantity = parseInt(this.formGroupForUpdate[i].controls.quantity.value);
          this.serviceArr[i].unit = this.formGroupForUpdate[i].controls.unit.value;
          this.serviceArr[i].prices = parseInt(this.formGroupForUpdate[i].controls.prices.value);

          this.serviceArrCheck[i].serviceId = '';
          this.serviceArrCheck[i].serviceName = '';
          this.serviceArrCheck[i].quantity = 0;
          this.serviceArrCheck[i].unit = '';
          this.serviceArrCheck[i].prices = 0;

          this.formGroupForUpdate[i] = this.formBuilder.group({
            serviceId: ['', [Validators.pattern("^SV\\d{4}$"), Validators.required]],
            serviceName: ['', [Validators.required]],
            quantity: ['', [Validators.required, Validators.min(1)]],
            unit: ['', [Validators.required]],
            prices: ['', [Validators.required, Validators.min(1000)]]
          });
        }
      }
    );

    console.log(this.serviceArrCheck);
    console.log(this.serviceArr);
  }

  ngAfterContentChecked(): void {
    if (this.formGroupForAdd.length > 0) {
      for (let i = 0; i < this.formGroupForAdd.length; i++) {
        if (this.formGroupForAdd[i].invalid) {
          this.confirmHaveErr = true;
        } else {
          this.confirmHaveErr = false;
        }
      }
    }

    let countDataNeedUpdate = 0;
    this.formGroupForUpdate.forEach(
      (o) => {
        if (o.get('serviceId')?.value != '') {
          countDataNeedUpdate++;
        }
      }
    );

    if (countDataNeedUpdate > 0) {
      for (let i = 0; i < this.formGroupForUpdate.length; i++) {
        if (this.formGroupForUpdate[i].get('serviceId')?.value != '') {
          if (this.formGroupForUpdate[i].invalid) {
            this.confirmHaveErr = true;
          } else {
            this.confirmHaveErr = false;
          }
        }
      }
    }
  }

  compareWith(c1: any, c2: any) {
    if (c1 != null && c2 != null) {
      return c1.id === c2.id;
    }
    return false;
  }

}
