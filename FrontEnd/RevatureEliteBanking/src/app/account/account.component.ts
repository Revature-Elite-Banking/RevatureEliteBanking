import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IAccounts } from '../accounts';
import { AccountsService } from '../accounts.service';
//placeholder data
import { ACCOUNTS } from '../mock-accounts';
import { AccountIdService } from '../services/account-id.service';
import * as alertyfy from 'alertifyjs';
import { LoginService } from '../login.service';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

 /* ******************************************************** Variables ********************************************************* */

  //accounts = ACCOUNTS; //mock-data: can be used to test the view of components without the need of the server, see mock-accounts.ts
  public accounts!: any; //instantiate an account object (type any) to be populated by server response 
  //instantiating variables that change
  public balance: number = 0;
  public type!: string;
  showTransfer = false;
  public from!: number;
  public to!: number;
  public amount!: number;
  public res = '';

 /* ********************************************************** Constructor ******************************************************** */ 

  //injecting our dependencies
  constructor(private http:HttpClient, private accountsService:AccountsService, private accountId:AccountIdService, private router:Router, private loginService:LoginService) { }

  /* ********************************************************** Methods ******************************************************** */ 
  
  //using the getAccounts() function from our accountsService 
  //then subscribing and redirecting the results/response to accountView() function, see below
  ngOnInit(): void {
    this.accountsService.getAccounts()
      .subscribe(data => this.accountView(data))
  }

  //Takes in an IAccounts object and assigns it to a variable; So that it can be referenced by our component
  accountView(accountsInfo:IAccounts){
    this.accounts = accountsInfo //assigning the data from the response to our instantiated accounts object
  }


  //Function: takes in corresponding account.id when user clicks on the account element and "saves it", see account-id.service.ts
  //then navigates to the transaction component
  red(accountID:number, accountBALANCE:number) {
    this.accountId.Id = accountID; //Id from account-id.service.ts is changed to whatever the accountID is
    this.accountId.Balance = accountBALANCE;
    this.router.navigate(['/transaction'])
  }

  //method that creates new account using user inputs. sends post request to server with those inputs
  submit() {
    var new_account:any = { 'balance':this.balance, 'type':this.type } 
    let url:string = 'http://localhost:8090/project3/account/new/'+localStorage.getItem('username')
    this.http.post(url,new_account).subscribe(r=>{});
    window.location.reload();
    console.log('account created')
  }

  //just to toggle the display of the transfer form
  transDisp() {
    if(this.showTransfer){
      this.showTransfer=false;
    }else{
      this.showTransfer=true;
    }
  }
  //just to display the server response to an invalid transfer
  resp(text:any) {
    this.res = text;
  }

  //copy pasted from submit(), takes user input and sends post request
  // the .subscribe part goes unused but is required for post requests, not sure why
  transfer() {
    var trans:any = { 'senderID':this.from, 'recipientID':this.to, 'amount':this.amount }
    let url:string = 'http://localhost:8090/project3/transaction/transfer/'+localStorage.getItem('username')
    this.http.post(url,trans,{responseType: 'text'}).subscribe(r=>this.resp(r));
    console.log(trans);
     

    alertyfy.set('notifier','position', 'top-right');
    var notification = alertyfy.notify(formatDate(Date.now(),'EEE, dd MMM YYYY hh:mm:ss','en-US') +" Hello "+localStorage.getItem('username') +" $"+this.amount+" is transfered from account " + this.from +" to "+this.to, 'success', 30, function(){  console.log('dismissed'); });
  }

  logout() {
    this.loginService.logout();
  }
}
