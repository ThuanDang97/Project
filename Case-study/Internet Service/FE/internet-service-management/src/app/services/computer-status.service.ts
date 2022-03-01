import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IStatus} from "../interface/IStatus";

@Injectable({
  providedIn: 'root'
})
export class ComputerStatusService {
  private URL_STATUS = 'http://localhost:8080/computer/status';
  constructor(private httpClient: HttpClient) { }
  getAllStatus(): Observable<IStatus>{
    return this.httpClient.get<IStatus>(this.URL_STATUS);
  }
}
