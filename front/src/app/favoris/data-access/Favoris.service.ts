import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AddFavorisRequest} from "./favoris.model";

@Injectable({
  providedIn: 'root'
})
export class FavorisService {

  private baseUrl: string = '/api';

  constructor(private http: HttpClient) { }

  addProductToFav(request: AddFavorisRequest): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/favourite/add`, request);
  }

  getListeFavArticleByAccount(id: number): Observable<any[]> {
    return this.http.post<any[]>(`${this.baseUrl}/favourites/${id}`, {});
  }

  removeFav(id: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/favourite/remove/${id}`, {});
  }
}
