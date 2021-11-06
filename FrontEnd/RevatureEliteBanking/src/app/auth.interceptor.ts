import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  //This interceptor will take any HttpRequest, even fetch requests, and clone and add the authorization header for security
  intercept(request: HttpRequest<any>, next: HttpHandler) : Observable<HttpEvent<any>> {
    if(this.authService.IsLoggedIn) {
      request = request.clone({
        setHeaders: {
          Authorization: "Bearer " + localStorage.getItem('tokenKey'),
          Accept: 'application/json',
          'Content-Type': 'application/json'
        }
      });
    }
    
    return next.handle(request);
  }
}