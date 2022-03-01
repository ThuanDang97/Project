import { Injectable } from '@angular/core';
import {Province} from "./Province";
import {District} from "./District";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AddressSelectService {
  private readonly API_URL = 'https://vapi.vnappmob.com/api';

  constructor(private _httpClient: HttpClient) {
  }

  getAllProvince(): Observable<any> {
    return this._httpClient.get<any>(this.API_URL+'/province' );
  }

  getAllDistrict(id: string|undefined): Observable<any> {
    const url = `${this.API_URL}/province/district/${id}`;
    return this._httpClient.get<any>(url);
  }

  getAllCommune(id:string|undefined):Observable<any>{
    const url = `${this.API_URL}/province/ward/${id}`;
    return this._httpClient.get<any>(url);
  }

  // getProvinces() {
  //   return [
  //     new Province(1, 'Hải Châu' ),
  //     new Province(2, 'Hòa Vang' ),
  //     new Province(3, 'Thanh Khê' ),
  //     new Province(4, 'Liên Chiểu' ),
  //     new Province(5, 'Sơn Trà' ),
  //     new Province(6, 'Ngũ Hành Sơn' )
  //   ];
  // }
  //
  // getDistricts() {
  //   return [
  //     new District(1, 1, 'Thanh Bình' ),
  //     new District(2, 1, 'Thuận Phước' ),
  //     new District(3, 1, 'Thạch Thang'),
  //     new District(4, 1, 'Hải Châu I'),
  //     new District(5, 1, 'Hải Châu II'),
  //     new District(6, 1, 'Phước Ninh'),
  //     new District(7, 1, 'Hòa Thuận Tây'),
  //     new District(8, 1, 'Hòa Thuận Đông'),
  //     new District(9, 1, 'Nam Dương'),
  //     new District(10, 1, 'Bình Hiên'),
  //     new District(11, 1, 'Bình Thuận'),
  //     new District(12, 1, 'Hòa Cường Bắc'),
  //     new District(13, 1, 'Hòa Cường Nam'),
  //
  //     new District(14, 2, 'Hòa Bắc' ),
  //     new District(15, 2, 'Hòa Liên'),
  //     new District(16, 2, 'Hòa Ninh' ),
  //     new District(17, 2, 'Hòa Sơn' ),
  //     new District(18, 2, 'Hòa Nhơn' ),
  //     new District(19, 2, 'Hòa Phú' ),
  //     new District(20, 2, 'Hòa Phong' ),
  //     new District(21, 2, 'Hòa Châu' ),
  //     new District(22, 2, 'Hòa Tiến' ),
  //     new District(23, 2, 'Hòa Phước' ),
  //     new District(24, 2, 'Hòa Khương' ),
  //
  //     new District(14, 3, 'Hòa Bắc' ),
  //     new District(15, 3, 'Hòa Liên'),
  //     new District(16, 3, 'Hòa Ninh' ),
  //     new District(17, 3, 'Hòa Sơn' ),
  //     new District(18, 3, 'Hòa Nhơn' ),
  //     new District(19, 3, 'Hòa Phú' ),
  //     new District(20, 3, 'Hòa Phong' ),
  //     new District(21, 3, 'Hòa Châu' ),
  //     new District(22, 3, 'Hòa Tiến' ),
  //     new District(23, 3, 'Hòa Phước' ),
  //     new District(24, 3, 'Hòa Khương' ),
  //
  //   ];
  // }
}
