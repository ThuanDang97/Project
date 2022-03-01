import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {TokenStorageService} from "../security/service/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  httpOptions: any;
  public APIPaymentCustomer: string = 'http://localhost:8080/payment/payCustomer';
  public APIGetPay: string = 'http://localhost:8080/payment/pay';
  public APIExchange: string = 'http://localhost:8080/payment/pay/exchange';
  public APIPay: string = 'http://localhost:8080/payment/pay';
  public APIPaypal: string = 'http://localhost:8080/payment/make/paypal';
  public APIList: string = 'http://localhost:8080/payment/list'
  public APISearch: string = 'http://localhost:8080/payment/search'

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer` + this.tokenStorage.getToken(),
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
      }),
    };
  }

  getPaymentCustomerById(customerId: any): Observable<any> {
    return this.http.get(this.APIPaymentCustomer + '/' + customerId,this.httpOptions);
  }

  getPaymentById(customerId: any): Observable<any> {
    return this.http.get(this.APIGetPay + '/' + customerId,this.httpOptions);
  }

  calculatorExchange(idPay: number, moneyRecived: number): Observable<any> {
    return this.http.post(this.APIExchange + '?id=' + idPay + '&moneyRecived=' + moneyRecived, {},this.httpOptions)
  }

  pay(idPay: number): Observable<any> {
    return this.http.post(this.APIPay + '?id=' + idPay, {},this.httpOptions)
  }

  paypal(idPay: number): Observable<any> {
    return this.http.post(this.APIPaypal + '?id=' + idPay, {},this.httpOptions)
  }

  getListPayment(): Observable<any> {
    return this.http.get(this.APIList,this.httpOptions);
  }

  search(searchName: string): Observable<any> {
    return this.http.get(this.APISearch + '?searchName=' + searchName,this.httpOptions)
  }

  getPageList(pageNum: number): Observable<any> {
    const url = `${this.APIList}?page=${pageNum}`;
    return this.http.get<any>(url,this.httpOptions);
  }

  getPageSearch(searchName: string, pageNum: number): Observable<any> {
    const url = this.APISearch + '?searchName=' + searchName + '&page=' + pageNum;
    return this.http.get<any>(url,this.httpOptions);
  }
}
