import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {TokenStorageService} from "./service/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {UserService} from "./service/user.service";

@Injectable({
  providedIn: 'root'
})
export class AuthAdminGuard implements CanActivate {
  private url!:string;
  constructor(private router: Router, private tokenStorageService: TokenStorageService, private toastr: ToastrService,private userService:UserService) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const currentUser = this.tokenStorageService.getUser();
    if (currentUser !== null) {
      if (currentUser.roles.includes('ROLE_ADMIN'||'ROLE_EMPLOYEE')) {
        return true;
      } else {
        this.router.navigate([this.router.url], {queryParams: {returnUrl: state.url}});
        this.toastr.error('Bạn không có quyền truy cập!', 'Thông báo');
        return false;
      }
    } else {
      this.router.navigate(['login'], {queryParams: {returnUrl: state.url}});
      return false;
    }
  }

}
