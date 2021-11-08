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

  parseTransactions(newInfo:any):any{
    let usableInfo:any[] =[]
    
    
    for (let i = 0; i < newInfo.length; i++){
      let tempArray =[]

      //this injects the parsed data into a new temporary array that will be added to the "main read array"

      if (newInfo[i].id === 1){ //replace 1 with account ID number
        tempArray.push(newInfo[i].id)
        tempArray.push(newInfo[i].amount) 
        tempArray.push(newInfo[i].date) 
        tempArray.push(newInfo[i].description)
        if (newInfo[i].status == null){
          tempArray.push("PENDING")
        } else{
          tempArray.push(newInfo[i].status)
        }
        tempArray.push(newInfo[i].type) 
        usableInfo.push(tempArray)  
        console.log(tempArray)
      }
      
    }
    console.log(usableInfo)
  return usableInfo
  }
  
  

}
