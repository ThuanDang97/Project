import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaymentListComponent } from './payment-list/payment-list.component';
import { PaymentCustomerComponent } from './payment-customer/payment-customer.component';

import { MatDialogModule } from '@angular/material/dialog';
import { ServiceDetailComponent } from './service-detail/service-detail.component';
import { HttpClientModule} from '@angular/common/http';
import { PaymentPayComponent } from './payment-pay/payment-pay.component';
import { MatTabsModule } from '@angular/material/tabs';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    PaymentListComponent,
    PaymentCustomerComponent,
    ServiceDetailComponent,
    PaymentPayComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
    HttpClientModule,
    MatTabsModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule
  ]
})
export class PayModule { }
