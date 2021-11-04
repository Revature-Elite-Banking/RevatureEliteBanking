import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { TransactionComponent } from './transaction/transaction.component';

const routes: Routes = [

  {
    path:"",
    component:LoginComponent
  },

  {
    path:"login",
    component:LoginComponent
  },

  {
    path:"registration",
    component:RegistrationComponent
  },

  {
    path:"transaction",
    component:TransactionComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
