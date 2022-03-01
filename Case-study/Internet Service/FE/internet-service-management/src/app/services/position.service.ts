import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent} from "@angular/common/http";
import {Observable} from "rxjs";
import {IPosition} from "../interface/IPosition";

@Injectable({
  providedIn: 'root'
})
export class PositionService {
  private API_POSITION = 'http://localhost:8080/position/listPosition';

  constructor(private httpClient: HttpClient) {

  }

  getPositionList(): Observable<IPosition[]> {
    return this.httpClient.get<IPosition[]>(this.API_POSITION);
  }


}
