import { Component, OnInit } from '@angular/core';
import { RegistrationDto } from '../models/registration-dto';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public username:string = "";
  public password:string = "";
  public email:string = "";
  public f_name:string = "";
  public l_name:string = "";
  public address:string = "";
  public city:string = "";
  public state:string = "";
  public zip_code:any = "";
  public userExists:boolean=false;
  public userError:string="";
  public emailError:string="";
 

  constructor(private rs:RegistrationService, private router:Router) { }

  ngOnInit(): void {
  }

  //register() will double check that all fields are filled before calling the registration service
  //it also checks the database to ensure that username and email are not currently in use by another user
  //primary input validation is handled in the html file using template driven validation
  register():void{
    if ((this.username == "") || (this.password == "") ||
        (this.email == "") || (this.f_name == "") ||
        (this.l_name == "") || (this.address == "") ||
        (this.city == "") || (this.state == "") ||
        (this.zip_code == "")){
      alert("Please fill all fields.");
      return;
    }
    else{
      
      this.rs.register(this.username, this.password, this.email, this.f_name, this.l_name, this.address, this.city, this.state, this.zip_code)
      .subscribe(
        resp=>{ 
          if(resp.status==201){
            console.log("account successfully created");
            this.router.navigate(['/login']);
          }
   
        },
        error=> {
          if(error.status==403){
            console.log("username already exists");
            this.userExists=true;
            this.userError=error.error;
          }
          if(error.status==406){
            console.log("Email already exists");
            this.userExists=true;
            this.emailError=error.error;
          }
    
        }
      );
      
    }
  }

}
