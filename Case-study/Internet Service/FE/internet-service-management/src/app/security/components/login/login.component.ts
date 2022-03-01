import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {TokenStorageService} from "../../service/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {LoadCssService} from "../../service/load-css.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string[] = [];
  constructor(private userService: UserService, private router: Router, private tokenStorage: TokenStorageService,
              private toastr: ToastrService,private loadCssService:LoadCssService) {
  }

  loginForm = new FormGroup({
      username: new FormControl('', [Validators.required,Validators.minLength(4)]),
      password: new FormControl('', [Validators.required,Validators.minLength(8)]),
    }
  );

  get getUserName() {
    return this.loginForm.get("username");
  }

  get getPassword() {
    return this.loginForm.get("password");
  }

  ngOnInit() {
    this.loadCssService.loadScript('assets/sercurity/js/main.js');
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      for (const role of this.roles) {
        if (role === 'ROLE_USER') {
          this.router.navigateByUrl('/home');
          break;
        } else {
          this.router.navigateByUrl('/admin');
        }
      }
    }
  }

  signIn() {
    this.userService.login(this.loginForm.value).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        for (const role of this.roles) {
          if (role === 'ROLE_USER') {
            this.router.navigateByUrl('/home');
            this.toastr.success("Đăng nhập thành công", 'Thông báo', {
              timeOut: 2000,
              extendedTimeOut: 1500
            });
            break;
          } else {
            this.router.navigateByUrl('/admin');
            this.toastr.success("Đăng nhập thành công", 'Thông báo', {
              timeOut: 2000,
              extendedTimeOut: 1500
            });
          }
        }
      },
      err => {
        console.log(err);
        if (err.status == 404) {
          this.toastr.error("Tài khoản hoặc mật khẩu không đúng!", 'Thông báo', {
            timeOut: 2000,
            extendedTimeOut: 1500
          });
          this.isLoginFailed = true;
        }
        // else {
        //   this.toastr.error('Username or password is incorrect', 'SingIn error', {
        //     timeOut: 2000,
        //     extendedTimeOut: 1500
        //   });
        //   this.errorMessage = err.error.message;
        //   this.isLoginFailed = true;
        // }
      }
    );
  }

  reset() {
    this.loginForm.reset();
  }

  reloadPage() {
    window.location.reload();
  }

}
