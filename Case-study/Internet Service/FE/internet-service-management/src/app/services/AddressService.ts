import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IDistrict} from "../interface/IDistrict";
import {IProvince} from "../interface/IProvince";

@Injectable({
  providedIn: "root"
})
export class AddressService {
  private readonly API_URL = "https://vapi.vnappmob.com/api/";

  constructor(private _httpClient: HttpClient) {
  }

  getAllProvince(): Observable<any> {
    return this._httpClient.get<any>(this.API_URL + '/province');
  }

  getAllDistrict(id: string|undefined): Observable<any> {
    const url = `${this.API_URL}/province/district/${id}`;
    return this._httpClient.get<any>(url);
  }

  getAllWard(id:string|undefined):Observable<any>{
    const url = `${this.API_URL}/province/ward/${id}`;
    return this._httpClient.get<any>(url);
  }
}
