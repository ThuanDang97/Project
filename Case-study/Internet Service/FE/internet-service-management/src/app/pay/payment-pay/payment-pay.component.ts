import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, MinValidator, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Exchange } from 'src/app/interface/exchange';
import { Payment } from 'src/app/interface/payment';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-payment-pay',
  templateUrl: './payment-pay.component.html',
  styleUrls: ['./payment-pay.component.css']
})
export class PaymentPayComponent implements OnInit {
  public formExchange!: FormGroup;
  payment!: Payment;
  resultMoney = 0;
  moneyRecived: any;
  statusExchange = false;
  constructor(
    private paymentService: PaymentService,
    public formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    public router: Router,
    public dialogRef: MatDialogRef<PaymentPayComponent>,
    @Inject(DOCUMENT) private document: Document,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    this.payment = this.data.data;
    this.formExchange = this.formBuilder.group({
      moneyRecived: ['', [Validators.required,
      Validators.min(this.payment.totalPayment),
      Validators.pattern('^[0-9]*$'),
      Validators.max(100000000)

      ]],
    })
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  calculatorExchange() {
    this.paymentService.calculatorExchange(this.payment.id, this.formExchange.value.moneyRecived).subscribe(data => {
      this.resultMoney = data.moneyRecived;
      this.statusExchange = true;
    })
  }

  pay() {
    this.paymentService.pay(this.payment.id).subscribe(data => {
      this.snackBar.open('Đã thanh toán thành công !!!', '', {
        duration: 2000,
        horizontalPosition: 'right',
        verticalPosition: 'top',
        panelClass: ["custom-style"]
      });
      // this.paymentService.getPaymentCustomerById(this.payment.id);
      this.dialogRef.close();
      // this.paymentService.getListPayment();
    })
  }

  paypal() {
    this.paymentService.paypal(this.payment.id).subscribe(data => {
      this.document.location.href = data.redirect_url;
    })
  }

}
