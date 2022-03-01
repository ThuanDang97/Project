import {Injectable} from "@angular/core";
import {AngularFireDatabase, AngularFireList} from "@angular/fire/compat/database";
import {ChatModel} from "../model/Chat.model";


@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private dbPath = '/chat';

  tutorialsRef: AngularFireList<ChatModel>;


  constructor(private db: AngularFireDatabase,) {
    this.tutorialsRef = db.list(this.dbPath);
  }

  getAll(room: string): AngularFireList<ChatModel> {
    return this.db.list(this.dbPath + "/" + room);
  }

  create(chat: ChatModel, room: string): any {
    return this.db.list(this.dbPath + "/" + room).push(chat);
  }

}
