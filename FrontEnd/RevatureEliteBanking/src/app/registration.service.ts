import {HttpClient, HttpHeaders, JsonpClientBackend } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationDto } from './models/registration-dto';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private url:string = "http://localhost:8090/project3/";
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    observe: 'response' as 'response'
  };

  constructor(private router:Router, private http:HttpClient) { }
/**
 * Functions to register the user details
 */
register(username:string, password:string, email:string, fname:string, lname:string, street:string, city:string, state:string, zip:string){
  let rdto = new RegistrationDto(username, password, email, fname, lname, street, city, state, zip);
  return this.http.post(this.url+"user",rdto,this.httpOptions);
}
  // async register(username:string, password:string, email:string, fname:string, lname:string, street:string, city:string, state:string, zip:string) {
  //   console.log(username + password + email + fname + lname + street + city + state + zip +"in service");
  //   let rdto = new RegistrationDto(username, password, email, fname, lname, street, city, state, zip);
  //   console.log(rdto);
  //   let response = await fetch(this.url + "user", { // need to update to correct endpoint
  //     method: "POST",
  //     body: JSON.stringify(rdto),
  //     credentials:"include",
  //     headers: {
  //       'Accept': 'application/json',
  //       'Content-Type': 'application/json'
  //       },
  //   })
  //   if (response.status == 201){
  //     let data = response.headers;
  //     console.log(data);
  //     console.log("account successfully created");
  //     this.router.navigate(['/login']);
  //   }
  //   else if(response.status == 403){
  //     let data = await response.text();
  //     console.log(data);
  //   }
  //   else{
  //     console.log("Invalid information entered");
  //   }
  // }
}
