import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IAccounts } from '../accounts';
import { AccountsService } from '../accounts.service';
//placeholder data
import { ACCOUNTS } from '../mock-accounts';
import { AccountIdService } from '../services/account-id.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  //accounts = ACCOUNTS; //mock-data: can be used to test the view of components without the need of the server, see mock-accounts.ts
  public accounts!: any; //instantiate an account object (type any) to be populated by server response 
  public balance: number = 0;
  public type!: string;
  showTransfer = false;
  public from!: number;
  public to!: number;
  public amount!: number;
  //public something:any = { 'balance':this.balance, 'type':this.type } 

  //injecting our dependencies
  constructor(private http:HttpClient, private accountsService:AccountsService, private accountId:AccountIdService, private router:Router) { }

  
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

  submit() {
    var new_account:any = { 'balance':this.balance, 'type':this.type } 
    let url:string = 'http://localhost:8090/project3/account/new/'+localStorage.getItem('username')
    this.http.post(url,new_account).subscribe(r=>{});
    window.location.reload();
    console.log('account created')
  }

  transDisp() {
    if(this.showTransfer){
      this.showTransfer=false;
    }else{
      this.showTransfer=true;
    }
  }

  transfer() {
    var trans:any = { 'senderID':this.from, 'recipientID':this.to, 'amount':this.amount }
    let url:string = 'http://localhost:8090/project3/transaction/transfer'
    this.http.post(url,trans,{responseType: 'text'}).subscribe(r=>{});
    console.log(trans);
  }
}
