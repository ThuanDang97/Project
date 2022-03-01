import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {IType} from "../interface/IType";

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  httpOptions: any;
  public API: string = 'http://localhost:8080/computer/type';

  constructor(private http: HttpClient ) {

  this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        // 'Authorization': `Bearer` + this.tokenStorage.getToken(),
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
      }),
    };
}

  getAllType():Observable<any> {
    return this.http.get<IType[]>(this.API, this.httpOptions);
  }
}
