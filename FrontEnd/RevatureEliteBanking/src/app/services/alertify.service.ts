import { Injectable } from '@angular/core';
import * as alertify from 'alertifyjs';

@Injectable({
  providedIn: 'root'
})
export class AlertifyService {

  constructor() { }
  success(message:string){
    alertify.alert().set('Notification', 'This is a new message!').show();
    alertify.success(message);
  }
   
}
