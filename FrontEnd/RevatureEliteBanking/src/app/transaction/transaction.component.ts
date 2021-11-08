import { Component, OnInit } from '@angular/core';

import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {
  
  constructor(private tranService:TransactionService) { }
  public transactionArray = this.tranService.transactionArray
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
   
    
  }

getInfo():void{
  
   let infoArray=this.tranService.parseTransactions(this.transArray); //calls function to get needed data from JSON object
  console.log(infoArray)
  this.infoArray = infoArray
  console.log(infoArray)
}

}
