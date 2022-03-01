import {Component, OnInit} from '@angular/core';
import {faPaperPlane, faSmileBeam} from '@fortawesome/free-solid-svg-icons';
import {faFacebookMessenger} from '@fortawesome/free-brands-svg-icons'
import {ChatModel} from "../../model/Chat.model";
import {FormControl, FormGroup} from "@angular/forms";
import {ChatService} from "../../services/chat.service";
import {map} from "rxjs/operators";
import {Router} from "@angular/router";
import {TokenStorageService} from "../../security/service/token-storage.service";

@Component({
  selector: 'app-chat-user',
  templateUrl: './chat-user.component.html',
  styleUrls: ['./chat-user.component.css']
})

export class ChatUserComponent implements OnInit {


  faFacebookMessenger = faFacebookMessenger;
  faPaperPlane = faPaperPlane;
  faSmileBean = faSmileBeam;

  chat: ChatModel = {};
  roomName: string;
  chats?: ChatModel[] = [];
  chatForm!: FormGroup;
  displayChat: string = "display:none;"
  today: string = new Date().toLocaleDateString().toString();
  checkToday?: boolean;
  checkYesterday?: boolean;
  yesterdayDate: Date = new Date(new Date());
  yesterday: string = '';
  todayDate ?: Date;
  dateArr: Set<any> = new Set();
  message = '';
  showEmojiPicker = false;

  toggleEmojiPicker() {
    console.log(this.showEmojiPicker);
    this.showEmojiPicker = !this.showEmojiPicker;
  }



  constructor(private chatService: ChatService, private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.roomName = this.tokenStorageService.getUser().account.userName;
    this.chatForm = new FormGroup({
      message: new FormControl('')
    });
    this.getAllMessages(this.roomName);
  }

  changeMess(event:any){
    this.message = event.target.value;
  }

  addEmoji(event:any) {
    const { message } = this;
    const text = `${message}${event.emoji.native}`;
    this.message = text;
  }

  onFocus() {
    console.log('focus');
    this.showEmojiPicker = false;
  }
  onBlur() {
    console.log('onblur')
  }

  getAllMessages(romeName: string) {
    this.chatService.getAll(romeName).snapshotChanges().pipe(
      map(changes =>
        changes.map(c =>
          ({key: c.payload.key, ...c.payload.val()})
        )
      )
    ).subscribe(data => {
      this.chats = data;

      data.forEach(item => {
        this.dateArr.add(item.date);
      })
      this.yesterdayDate.setDate(this.yesterdayDate.getDate() - 1);
      this.yesterday = this.yesterdayDate.toLocaleDateString();

      this.checkToday = this.checkHasDay(this.dateArr, this.today);
      this.checkYesterday = this.checkHasDay(this.dateArr, this.yesterday);
    })
  }

  sendMessage(chat: ChatModel) {
    this.chatService.create(chat, this.roomName);
  }

  onsubmit() {
    this.chat.userName = "room1";
    this.todayDate = new Date();
    this.chat.date = this.todayDate.toLocaleDateString().toString();
    this.chat.time = this.todayDate.toString();
    this.chat.status = "sent";
    this.chat.message = this.chatForm.value.message;
    this.sendMessage(this.chat);
    this.chatForm = new FormGroup({
      message: new FormControl('')
    });
  }

  displayChatBox() {
    if (this.displayChat == "display:block;") {
      this.displayChat = "display:none;";
    } else {
      this.displayChat = "display:block;";
    }
  }

  checkHasDay(arr: Set<any>, day: string): boolean {
    for (let i of arr) {
      if (i.toString() == day) {
        return true;
      }
    }
    return false;
  }
}
