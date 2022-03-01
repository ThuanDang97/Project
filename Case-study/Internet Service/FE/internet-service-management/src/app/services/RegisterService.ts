import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CustomerRegister} from "../model/CustomerRegister";
import {TokenStorageService} from "../security/service/token-storage.service";

@Injectable({
  providedIn: "root"
})
export class RegisterService {
  private readonly API_URL = "http://localhost:8080/customer";
  httpOptions: any;

  constructor(private _httpClient: HttpClient, private tokenStorage: TokenStorageService) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer` + this.tokenStorage.getToken(),
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
      }),
    };
  }

  checkUser(userName: string): Observable<boolean> {
    return this._httpClient.get<boolean>(this.API_URL + "/checkUser/" + userName);
  }

  register(customerRegister: CustomerRegister): Observable<any> {
    const url = `${this.API_URL}/register`
    return this._httpClient.post<any>(url, customerRegister,this.httpOptions);
  }

  checkEmail(email: string): Observable<boolean> {
    return this._httpClient.get<boolean>(this.API_URL + "/checkEmail/" + email);
  }
}
