import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DataForComputer, DataForMonth, DataForTopAccount} from "../statistical/IStatistical";

@Injectable({
  providedIn: 'root'
})
export class StatisticalServiceService {
  URL_API: string = 'http://localhost:8080/statistical/';
  constructor(private http: HttpClient) { }

  getDataByComputer(startTime: string, endTime: string): Observable<DataForComputer[]>{
    return this.http.get<DataForComputer[]>(this.URL_API+'view-by-computer?startTime=' + startTime + '&endTime=' + endTime);
  }

  getDataByMonth(startTime: string, endTime: string): Observable<DataForMonth[]>{
    return this.http.get<DataForMonth[]>(this.URL_API+'view-by-month?startTime=' + startTime + '&endTime=' + endTime);
  }

  getDataByAccount(startTime: string, time: string): Observable<DataForTopAccount[]>{
    let endTime = new Date(startTime);
    endTime.setMonth(endTime.getMonth()+1);
    switch (time) {
      case '1week':
        endTime.setDate(endTime.getDate()+7);
        break;
      case '2week':
        endTime.setDate(endTime.getDate()+14);
        break;
      case '1month':
        endTime.setMonth(endTime.getMonth()+1);
        break;
      case '1quarter':
        endTime.setMonth(endTime.getMonth()+3);
        break;
      case '2quarter':
        endTime.setMonth(endTime.getMonth()+6);
        break;
      case '1year':
        endTime.setFullYear(endTime.getFullYear()+1);
        break;
    }
    let endTimeString = endTime.getFullYear() + "-" + (endTime.getMonth()<10?"0"+endTime.getMonth():endTime.getMonth()) + "-" +
      (endTime.getDate()<10?"0"+endTime.getDate():endTime.getDate());
    // console.log('startTime: ' + startTime + ' : ' + 'endTime: ' + endTimeString);
    return this.http.get<DataForTopAccount[]>(this.URL_API+'view-by-account?startTime=' + startTime +
      '&endTime=' + endTimeString);
  }
}
