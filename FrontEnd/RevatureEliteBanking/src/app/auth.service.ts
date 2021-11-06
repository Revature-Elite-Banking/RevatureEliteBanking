import { Injectable } from '@angular/core';

import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpBackend } from '@angular/common/http';

import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError} from 'rxjs/operators'

import { User } from './models/user';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedIn = new BehaviorSubject<boolean>(this.tokenAvailable());
  url: string = "http://localhost:8090/project3/"
  tokenKey: string = 'access_token'
  constructor(
    private http: HttpClient,
    public router: Router,
    public handler: HttpBackend
  ) { }

  register(user: User): Observable<User> {
    let http = new HttpClient(this.handler)
    return http.post<any>(this.url + "user", user)
      .pipe(
        catchError(this.handleError)
      )
  }

  login(credentials: string) {
    this.http.post<any>(this.url + "login", credentials)
      .subscribe(async (res : any) => {
        this.setToken(res.token)
      })
  }

  async setToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }


  getToken(){
    return localStorage.getItem(this.tokenKey)
  }

  get IsLoggedIn(){
    return this.loggedIn.asObservable();
  }

  logout(){
    localStorage.clear();
    this.router.navigate(['login']);
  }

  handleError(error: HttpErrorResponse){
    let msg = '';
    if (error.error instanceof ErrorEvent){
      // Client-side error
      msg = error.error.message
    } else {
      // Server-side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }

  tokenAvailable(): boolean{
    return !this.getToken();
  }
}
