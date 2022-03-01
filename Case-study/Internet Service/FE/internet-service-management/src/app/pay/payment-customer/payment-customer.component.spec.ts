import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentCustomerComponent } from './payment-customer.component';

describe('PaymentCustomerComponent', () => {
  let component: PaymentCustomerComponent;
  let fixture: ComponentFixture<PaymentCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentCustomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
