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

  intercept(request: HttpRequest<any>, next: HttpHandler) : Observable<HttpEvent<any>> {
    if(this.authService.IsLoggedIn) {
      request = request.clone({
        setHeaders: {
          Authorization: "Bearer " + localStorage.getItem('tokenKey')
        }
      });
    }
    
    return next.handle(request);
  }
}