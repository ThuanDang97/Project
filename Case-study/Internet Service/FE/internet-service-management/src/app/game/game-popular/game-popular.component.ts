import {Component, OnInit} from '@angular/core';
import {GameServiceService} from "../../services/game-service.service";
import {IGame} from "../IGame";

@Component({
  selector: 'app-game-popular',
  templateUrl: './game-popular.component.html',
  styleUrls: ['./game-popular.component.css']
})
export class GamePopularComponent implements OnInit {

  game!: IGame[];
  searchName!: string;
  category!: string;
  totalPage!: number;
  pageNow: number = 1;
  keyOption = '';

  constructor(private service: GameServiceService) {
  }

  ngOnInit(): void {
    this.getAllGame();
    this.getCategory();
  }

  getPage(page: number) {
    this.service.getPage(page).subscribe(data => {
      this.game = data.content;
      this.pageNow = data.pageable.pageNumber + 1;
    })
  }

  getAllGame() {
    this.service.getAll().subscribe(data => {
      this.game = data.content;
      this.totalPage = data.totalPages;
    })
  }

  search() {
    this.service.search(this.searchName).subscribe(data => {
      this.game = data.content;
    });
  }

  searchOption() {
    this.service.searchCategory(this.keyOption).subscribe(data =>{
      this.game = data.content;
    })
  }

  getCategory(){
    this.service.getCategory().subscribe(data =>{
      this.category = data;
    })
  }
}
