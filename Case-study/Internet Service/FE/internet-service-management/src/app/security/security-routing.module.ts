import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {AdminComponent} from "./components/admin/admin.component";
import {AuthGuard} from "./auth.guard";
import {AuthAdminGuard} from "./auth-admin.guard";


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent,canActivate:[AuthGuard]},
  {path: 'admin', component: AdminComponent,canActivate:[AuthAdminGuard]}

];

@NgModule({
  imports: [RouterModule.forChild(routes) ],
  exports: [RouterModule],
})
export class SecurityRoutingModule {

}
