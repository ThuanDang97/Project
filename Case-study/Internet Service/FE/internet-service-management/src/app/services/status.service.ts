import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IStatus} from "../interface/IStatus";

@Injectable({
  providedIn: 'root'
})
export class StatusService {

  public API: string = 'http://localhost:8080/computer/status';

  constructor(private http: HttpClient) {
  }

  getAllStatus(): Observable<IStatus[]> {
    return this.http.get<IStatus[]>(this.API);
  }
}
