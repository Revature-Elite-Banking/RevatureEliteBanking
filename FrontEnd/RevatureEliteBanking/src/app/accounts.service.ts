import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { IAccounts } from './accounts';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  //Variables: (1) Location of the handler to retrieve accounts; (2) the username of the current user
  private url:string = 'http://localhost:8090/project3/account/view/';
  username = localStorage.getItem("username") //the username gets stored during login, see login service
  
  //inject dependency HttpClient
  constructor(private http:HttpClient) { }
  
  //Function that returns an Observable of type IAccounts --> See accounts.ts
      // makes a get request to handler (url+'username')
  getAccounts(): Observable<IAccounts>{
    return this.http.get<IAccounts>(this.url + this.username)
  }
  
}
