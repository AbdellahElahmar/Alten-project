import {Component, OnInit} from '@angular/core';
import { MessageService } from 'primeng/api';
import {CardModule} from "primeng/card";
import {ToastModule} from "primeng/toast";
import {NgForOf, NgIf} from "@angular/common";
import {ButtonDirective} from "primeng/button";
import {FavorisService} from "./data-access/Favoris.service";

@Component({
  selector: 'app-favorites',
  templateUrl: './favoris.component.html',
  styleUrls: ['./favoris.component.css'],
  standalone: true,
  imports: [
    CardModule,
    ToastModule,
    NgForOf,
    ButtonDirective,
    NgIf
  ],
  providers: [MessageService]
})


export class FavorisComponent implements OnInit{


  favorites: any[] = [];

  constructor(
    private favorisService: FavorisService
  ) {}
  ngOnInit() {
    this.loadData()
  }
  loadData() {
    this.favorisService.getListeFavArticleByAccount(Number(localStorage.getItem('accountId')))
      .subscribe(next => {
        this.favorites = next
      })
  }

  removeFromFavorites(favorite: any) {
    this.favorisService.removeFav(favorite.id)
      .subscribe(next => {
        this.loadData()
      })

    this.favorites = this.favorites.filter(f => f.id !== favorite.id);

  }





}
