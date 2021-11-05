import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { IAccounts } from '../accounts';
import { AccountsService } from '../accounts.service';
//placeholder data
import { ACCOUNTS } from '../mock-accounts';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  // placeholder info
  accounts = ACCOUNTS;
  
  //accounts2!: IAccounts;
  id = 0
  balance = 0
  type = ''
  //public accounts!: IAccounts; 

  constructor(
    private http:HttpClient,
    private accountsService:AccountsService
  ) { 
  }

  ngOnInit(): void {
    this.accountsService.getAccounts()
      .subscribe(data => this.accountView(data))
  }

  accountView(accountsInfo:IAccounts){
    //this.id = accountsInfo.id
    this.balance = accountsInfo.balance
    this.type = accountsInfo.type    
    //accounts = accountsInfo
  }

  red(userID:number) {
    console.log(userID)
  }

}
