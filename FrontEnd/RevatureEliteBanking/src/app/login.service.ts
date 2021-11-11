import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { LoginDto } from './models/login-dto';
import { User } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url:string = 'http://localhost:8090/project3/';


  constructor(private http:HttpClient, private router:Router, private as:AuthService) { }

  getUser() {
    return this.http.get(this.url + "login") as Observable<User>; //may need to change to proper handler
  }

  //This is the login function used to verify login information and store the jwt in the localStorage
  async login(username:string, password:string) {
    // console.log(username + password + "in service");
    let ldto = new LoginDto(username,password);
     console.log(ldto);
    
    //Using fetch to verify login information
    let response = await fetch(this.url + "login", { //may need to change to proper handler
      method: "POST",
      body: JSON.stringify(ldto),
      credentials:"include",
      //Need to include these headers to get around Cors issues
      headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
      },
    })
    
    //If the login status returns true then we store the key as tokenKey
    if (response.status == 200){
      console.log("login successful");
      let data = await response.text();
      //This is storing the tokenKey in localStorage
      await localStorage.setItem('tokenKey', data);
      // console.log(localStorage.getItem('tokenKey'));
      //This should then pull the user's information to store for later access
      let response2 = await fetch(this.url + "user/" + username, {
        method: "GET",
        credentials:"include",
        headers:{
          'Authorization': 'Bearer ' + localStorage.getItem('tokenKey')
        }
      })
      if (response2.status === 200){
        let data = await response2.json();
        localStorage.setItem('user', JSON.stringify(data));

        //This code is to create the user object in TS.
        //You cannot store objects locally but you can stringify an object and parse it.
        // let retrievedObject = localStorage.getItem('user');
        // console.log(JSON.parse(String(retrievedObject)));
      }
      this.router.navigate(['/account']) //route to users homepage or wherever we want after successful login
      //this.router.navigate(['/home']) //rout to users homepage or wherever we want after successful login
      localStorage.setItem('username', username);
    }
    else{
      console.log("Username or password not found");
    }

  }

  logout() {
    localStorage.clear();
    setTimeout(()=>{
      window.location.reload();
    }, 100);
    this.router.navigate(['login']);
  }
}