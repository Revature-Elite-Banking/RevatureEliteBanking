import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  public allTransactions:any
  public transactionArray:any 
  // dependency inject HttpClient
  constructor(private http:HttpClient) { }

  // this function will get all transaction from the db
  getTransactions():Observable<any>{
    this.allTransactions =  this.http.get("http://localhost:8090/project3/transaction", {withCredentials: true}) as Observable<any>;
    return this.allTransactions
  }

  parseTransactions():any{
    for (let i = 0; i < this.allTransactions.length; i++){
      let tempArray =[]
      //this injects the parsed data into a new temporary array that will be added to the "main read array"
      if (this.allTransactions.account.id == 1){
        tempArray.push(this.allTransactions[i].id)
        tempArray.push(this.allTransactions[i].amount) 
        tempArray.push(this.allTransactions[i].date) 
        tempArray.push(this.allTransactions[i].description) 
        tempArray.push(this.allTransactions[i].status)
        tempArray.push(this.allTransactions[i].type) 
        this.transactionArray.push(tempArray)  
      }
      
    }
  }

}
