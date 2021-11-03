import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  // dependency inject HttpClient
  constructor(private http:HttpClient) { }

  // this function will get all transaction from the db
  getTransactions():Observable<any>{
    return this.http.get("http://localhost:8090/project3/transaction", {withCredentials: true}) as Observable<any>;
  }
}
