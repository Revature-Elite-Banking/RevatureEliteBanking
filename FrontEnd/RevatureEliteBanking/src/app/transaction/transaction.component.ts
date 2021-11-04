import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  constructor(private tranService:TransactionService) { }

  transArray: any[] = [];

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
  }

  /*
  onClickGetHistory(): void {
    // subscribe to the transaction observable
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
  */

}
