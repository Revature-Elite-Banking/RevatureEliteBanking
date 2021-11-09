import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
//This service is used to transfer data (accountID) between unrelated/sibling components (account --> transaction)
export class AccountIdService {

  Id = 0; //default value for accountID, will change when "red()" function is called, see account.component.ts
  Balance = 0;

  constructor() { }
}
