import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ChatAdminComponent} from './chat-admin/chat-admin.component';
import {ChatUserComponent} from './chat-user/chat-user.component';
import {ReactiveFormsModule} from "@angular/forms";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {PickerModule} from "@ctrl/ngx-emoji-mart";


@NgModule({
  declarations: [
    ChatAdminComponent,
    ChatUserComponent
  ],
  exports: [
    ChatAdminComponent,
    ChatUserComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    PickerModule
  ]
})
export class ChatModule {
}
