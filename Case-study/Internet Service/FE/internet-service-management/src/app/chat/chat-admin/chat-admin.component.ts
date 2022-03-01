import {Component, OnInit} from '@angular/core';
import {ChatService} from "../../services/chat.service";
import {ChatModel} from "../../model/Chat.model";
import {map} from "rxjs/operators";
import {FormControl, FormGroup} from "@angular/forms";
import {faSmileBeam} from "@fortawesome/free-solid-svg-icons";


@Component({
  selector: 'app-chat-admin',
  templateUrl: './chat-admin.component.html',
  styleUrls: ['./chat-admin.component.css']
})
export class ChatAdminComponent implements OnInit {

  faSmileBean = faSmileBeam;
  chat: ChatModel = {};
  roomName: string = "room1";
  chats: ChatModel[] = [];
  chatForm!: FormGroup;
  today: string = new Date().toLocaleDateString().toString();
  checkToday?: boolean;
  checkYesterday?: boolean;
  yesterdayDate: Date = new Date(new Date());
  yesterday: string = '';
  todayDate ?: Date;
  dateArr: Set<any> = new Set();
  statusSent: boolean = false;
  message = '';
  showEmojiPicker = false;

  toggleEmojiPicker() {
    console.log(this.showEmojiPicker);
    this.showEmojiPicker = !this.showEmojiPicker;
  }

  constructor(private chatService: ChatService) {
    this.getAllMessages(this.roomName);
  }

  ngOnInit(): void {
    this.chatForm = new FormGroup({
      message: new FormControl('')
    });
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
    this.chatService.create(chat, this.roomName).then(()=>{
      this.statusSent = false;
      this.chatForm = new FormGroup({
        message: new FormControl('')
      });
    });
  }

  onsubmit() {
    this.chat.userName = "admin 1";
    this.todayDate = new Date();
    this.chat.date = this.todayDate.toLocaleDateString().toString();
    this.chat.time = this.todayDate.toString();
    this.chat.status = "sent";
    this.chat.message = this.chatForm.value.message;
    this.sendMessage(this.chat);
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
