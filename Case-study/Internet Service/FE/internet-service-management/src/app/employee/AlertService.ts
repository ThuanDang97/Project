import {ToastrService} from "ngx-toastr";
import {Injectable} from "@angular/core";
@Injectable({
  providedIn: 'root'
})
export class AlertService {
  constructor(
    private toast: ToastrService
  ) {}
  // @ts-ignore
  showAlertSuccess(message){
    this.toast.success(message, 'Thông báo:');
  }
  // @ts-ignore
  showMessageErrors(message){
    this.toast.error(message, "Thông báo:")
  }
}
