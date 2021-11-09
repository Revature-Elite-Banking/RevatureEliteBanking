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

  constructor(private tranService:TransactionService, private accountId:AccountIdService) { }

  transArray: any[] = [];
  infoArray:any


  ngOnInit(): void {
    // subscribe to the transaction observable
    //*
    this.tranService.getTransactions().subscribe(
      (allTransactions:any)=>{
        this.transArray = allTransactions;
        console.log(this.transArray);
      },
      ()=>{
        console.log("No information");
      }
    );
   
    
    //*/
    console.log(this.accountId.Id)
  }

getInfo():void{
  
   let infoArray=this.tranService.parseTransactions(this.transArray); //calls function to get needed data from JSON object
  console.log(infoArray)
  this.infoArray = infoArray
  console.log(infoArray)
}

}
