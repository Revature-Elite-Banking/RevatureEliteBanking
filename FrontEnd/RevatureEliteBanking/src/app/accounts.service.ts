import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { IAccounts } from './accounts';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http:HttpClient) { }

  getAccounts(): Observable<IAccounts>{
    return this.http.post<IAccounts>('http://localhost:8090/user/get', localStorage.getItem("id_token"))
  }
}
