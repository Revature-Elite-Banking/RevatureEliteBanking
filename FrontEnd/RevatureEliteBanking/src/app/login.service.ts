import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginDto } from './models/login-dto';
import { User } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url:string = "http://localhost:8090/"


  constructor(private http:HttpClient, private router:Router) { }

  getUser() {
    return this.http.get(this.url + "login") as Observable<User>; //may need to change to proper handler
  }

  async login(username:string, password:string) {
    console.log(username + password + "in service");
    let ldto = new LoginDto(username,password);
    console.log(ldto);
    let response = await fetch(this.url + "login", { //may need to change to proper handler
      method: "POST",
      body: JSON.stringify(ldto),
      credentials:"include"
    })
    if (response.status == 200){
      console.log("login successful");
      
      localStorage.setItem('username', username);
      //this.router.navigate(['/home']) //rout to users homepage or wherever we want after successful login
    }
    else{
      console.log("Username or password not found");
    }
  }
}
