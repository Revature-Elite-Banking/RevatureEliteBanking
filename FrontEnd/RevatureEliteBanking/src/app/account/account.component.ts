import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { IAccounts } from '../accounts';
import { AccountsService } from '../accounts.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  // placeholder info
  name = ''
  username = ''
  chips = 10
  public accounts!: IAccounts; 

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
    this.name = accountsInfo.name
    this.username = accountsInfo.username
    this.chips = accountsInfo.chipCount
    
  }

}
