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

  //accounts = ACCOUNTS; //mock-data: can be used to test view of components without the need of the server
  public accounts!: any; //account object to be populated by server response 

  //injecting our dependencies
  constructor(
    private http:HttpClient,
    private accountsService:AccountsService
  ) { 
  }

  //using the getAccounts() function from our accountsService 
  //then subscribing and redirecting the results/response to accountView() function, see below
  ngOnInit(): void {
    this.accountsService.getAccounts()
      .subscribe(data => this.accountView(data))
  }

  //Takes in an IAccounts object and assigns it to a variable; So that it can be referenced by our component
  accountView(accountsInfo:IAccounts){
    this.accounts = accountsInfo 
  }

  //function for when user clicks on account element
  red(accountID:number) {
    console.log(accountID)
  }

}
