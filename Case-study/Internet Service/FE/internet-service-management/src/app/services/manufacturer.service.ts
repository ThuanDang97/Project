import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IStatus} from "../interface/IStatus";
import {IManufacturer} from "../interface/IManufacturer";
import {Manufacturer} from "../interface/Manufacturer";

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService {

  public API: string = 'http://localhost:8080/computer/manufacturer';

  constructor(private http: HttpClient) {
  }

  getAllManufacturer():Observable<IManufacturer[]> {
    return this.http.get<IManufacturer[]>(this.API);
  }
}
