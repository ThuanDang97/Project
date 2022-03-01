import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {IGame} from "../game/IGame";
import {UserService} from "../security/service/user.service";
import {TokenStorageService} from "../security/service/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class GameServiceService {
  httpOptions: any;

  constructor(private http:HttpClient,private tokenStorage: TokenStorageService) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer` + this.tokenStorage.getToken(),
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
      }),
    };
  }
  URL = "http://localhost:8080/games/";

  getAll(): Observable<any> {
    return this.http.get<any>(this.URL,this.httpOptions);
  }

  finById(id:number):Observable<any>{
    return this.http.get<any>(this.URL+'/'+id,this.httpOptions);
  }

  delete(id:number):Observable<any>{
    return this.http.delete<any>(this.URL+'/'+id,this.httpOptions);
  }

  search(key:String):Observable<any>{
    return this.http.get<any>(this.URL+"/search?key="+key,this.httpOptions);
  }

  searchCategory(key:String):Observable<any>{
    return this.http.get<any>(this.URL+"/searchCategory?key="+key,this.httpOptions);
  }

  getPage(key:number):Observable<any>{
    const url = `${this.URL}?page=${key}`;
    return this.http.get<any>(url,this.httpOptions);
  }

  getCategory():Observable<any>{
    return this.http.get<any>(this.URL+"/category",this.httpOptions)
  }


}
