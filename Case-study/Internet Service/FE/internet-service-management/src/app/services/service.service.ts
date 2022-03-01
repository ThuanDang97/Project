import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {IService} from "../interface/IService";
import { FormGroup } from '@angular/forms';
import {TokenStorageService} from "../security/service/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  API:string = "http://localhost:8080/service";
  statusSource = new BehaviorSubject<boolean>(false);
  currentMessage = this.statusSource.asObservable();
  httpOptions:any;

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

  getAllService():Observable<any>{
    return this.http.get<any>(this.API+'/list',this.httpOptions);
  }

  deleteServicesByID(servicesID: string): Observable<any>{
    return this.http.delete<any>(this.API + '/delete/' + servicesID,this.httpOptions);
  }
  deleteAllServices(): Observable<any>{
    return this.http.delete<any>(this.API + '/deleteAll',this.httpOptions);
  }

  getServicesById(servicesID: string):Observable<any>{
    return this.http.get<any>(this.API + '/list/' + servicesID,this.httpOptions);
  }

  search(searchName:string):Observable<any>{
    return this.http.get<any>(this.API + '/search' +'?searchName=' + searchName,this.httpOptions
    );
  };

  getPageList(pageNum: number,searchName:string):Observable<any>{
    const url = this.API + '/search' + '?page=' + pageNum +'&searchName=' + searchName;
    return this.http.get<any>(url,this.httpOptions);
  }

  addOrUpdate(formGroup: FormGroup, action: any): Observable<any> {
    const id = formGroup.controls.serviceId.value;
    if (action == "add") {
      return this.http.post(this.API + '/create', formGroup.value,this.httpOptions);
    } else {
      return this.http.patch(this.API + `/update/${id}`, formGroup.value,this.httpOptions);
    }
  }
}
