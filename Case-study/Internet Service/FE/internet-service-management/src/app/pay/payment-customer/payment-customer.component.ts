import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Payment } from 'src/app/interface/payment';
import { PaymentService } from 'src/app/services/payment.service';
import { PaymentPayComponent } from '../payment-pay/payment-pay.component';
import { ServiceDetailComponent } from '../service-detail/service-detail.component';

@Component({
  selector: 'app-payment-customer',
  templateUrl: './payment-customer.component.html',
  styleUrls: ['./payment-customer.component.css']
})
export class PaymentCustomerComponent implements OnInit {
  public payment!: Payment;
  public message!: String;
  constructor(
    public paymentService: PaymentService,
    public dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.message = params['message'];
    });
    if (this.message != null) {
      this.snackBar.open('Đã thanh toán thành công !!!', '', {
        duration: 2000,
        horizontalPosition: 'right',
        verticalPosition: 'top',
        panelClass: ["custom-style"]
      });
      this.paymentService.getPaymentById(1).subscribe(data => {
        this.payment = data;
      })
    }
    this.paymentService.getPaymentCustomerById(1).subscribe(data => {
      this.payment = data;
    })
  }

  openDialogDetail(payId: any): void {
    this.paymentService.getPaymentById(payId).subscribe(dataPay => {
      const dialogRef = this.dialog.open(ServiceDetailComponent, {
        width: '800px',
        data: { data: dataPay },
        disableClose: true
      });

      dialogRef.afterClosed().subscribe(result => {
        this.ngOnInit();
      });
    })
  }

  openDialogPayment(payId: any): void {
    this.paymentService.getPaymentById(payId).subscribe(dataPay => {
      const dialogRef = this.dialog.open(PaymentPayComponent, {
        width: '600px',
        data: { data: dataPay },
        disableClose: true
      });

      dialogRef.afterClosed().subscribe(result => {
        // this.ngOnInit();
        this.paymentService.getPaymentById(1).subscribe(data => {
          this.payment = data;
        })
      });
    })
  }

}

