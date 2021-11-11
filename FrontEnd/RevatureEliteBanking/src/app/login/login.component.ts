import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private ls:LoginService, private router:Router) { }

  public username:string = "";
  public password:string = "";

  public user:any = null;

  ngOnInit(): void {
    
  }
  
  //login() will check that both fields have been filled before calling the login service
  login():void{
    if((this.username == "") || (this.password == "")){
      alert("Please fill all fields.");
      return;
    }
    else{
      //call to login() in the login service
      this.ls.login(this.username,this.password);
    }  
  }
  
  //register() will simply route the user to the registration component
  register(){
    this.router.navigate(['/registration']);
  }
}