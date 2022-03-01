import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PaymentService } from 'src/app/services/payment.service';
import { PaymentPayComponent } from '../payment-pay/payment-pay.component';
import { ServiceDetailComponent } from '../service-detail/service-detail.component';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css']
})
export class PaymentListComponent implements OnInit {
  public payments: any;
  searchName: string = "";
  page: any;
  indexPagination: number = 1;
  totalPagination: number = 0;
  constructor(
    public paymentService: PaymentService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.getAllPayment();
  }

  getPage(pageNum: number) {
    this.paymentService.getPageSearch(this.searchName, pageNum).subscribe(
      data => {
        this.payments = data.content;
        this.indexPagination = data.pageable.pageNumber + 1;
      }
    )
  }

  getAllPayment() {
    this.paymentService.getListPayment().subscribe(data => {
      this.payments = data.content;
      this.totalPagination = data.totalPages;
    })
  }

  openDialogDetail(payId: any): void {
    this.paymentService.getPaymentById(payId).subscribe(dataPay => {
      const dialogRef = this.dialog.open(ServiceDetailComponent, {
        width: '800px',
        data: { data: dataPay },
        disableClose: true
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
        this.ngOnInit();
      });
    })
  }

  search() {
    this.paymentService.search(this.searchName).subscribe(data => {
      this.payments = data['content'];
    })
  }

}
