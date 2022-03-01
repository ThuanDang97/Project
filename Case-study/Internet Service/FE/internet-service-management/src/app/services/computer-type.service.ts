import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IType} from "../interface/IType";

@Injectable({
  providedIn: 'root'
})
export class ComputerTypeService {
  private URL_TYPE = 'http://localhost:8080/computer/type';
  constructor(private httpClient: HttpClient) { }
  getAllType():Observable<IType>{
    return this.httpClient.get<IType>(this.URL_TYPE);
  }
}
