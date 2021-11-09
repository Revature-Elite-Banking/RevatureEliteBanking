import { Component, OnInit } from '@angular/core';
import { AccountIdService } from '../services/account-id.service';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {
  
  
  public transactionArray = this.tranService.transactionArray

  constructor(private tranService:TransactionService, public accountId:AccountIdService) { }

  transArray: any[] = [];
  infoArray:any


  ngOnInit(): void {
    // subscribe to the transaction observable
    this.tranService.getTransactions().subscribe(
      (allTransactions:any)=>{
        this.transArray = allTransactions;
      },
      ()=>{
        console.log("No information");
      }
    );
   
    

    console.log(this.accountId.Id)
  }

getInfo():void{
  let transArray=this.tranService.findAccountTransactions(this.transArray, this.accountId.Id) //gets the correct transactions in relation to account number
  if (transArray == []){
    transArray = [["", "", "", "No Transactions Present", "", ""]]
  }else{
    let infoArray=this.tranService.parseTransactions(transArray); //calls function to get needed data from JSON object
    this.infoArray = infoArray
  }
}

}
