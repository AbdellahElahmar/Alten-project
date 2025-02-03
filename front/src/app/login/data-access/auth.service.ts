import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LoginRequest, RegisterRequest} from "./Auth.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'api/token';
  private accountApiUrl = 'api/account';

  constructor(private http: HttpClient) {}

  login(request: LoginRequest): Observable<any> {
    return this.http.post<any>(this.apiUrl, request);
  }

  saveToken(id: any, email: string, token: string) {
    localStorage.setItem('accountId', id);
    localStorage.setItem('jwtToken', token);
    localStorage.setItem('email', email);
    console.log("account id " + localStorage.getItem("accountId"))
  }

  createAccount(request: RegisterRequest): Observable<any> {
    return this.http.post<any>(this.accountApiUrl, request);
  }

  getToken() {
    return localStorage.getItem('jwtToken');
  }

  logout() {
    localStorage.removeItem('jwtToken');
  }
}
