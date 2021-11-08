import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../registration.service';

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
  public zip_code:string = "";

  constructor(private rs:RegistrationService) { }

  ngOnInit(): void {
  }

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
      console.log(this.username + this.password);
      this.rs.register(this.username, this.password, this.email, this.f_name, this.l_name, this.address, this.city, this.state, this.zip_code);
      
    }
  }

}
