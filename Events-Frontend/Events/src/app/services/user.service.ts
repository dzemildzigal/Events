import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const CONSTANTS: any = {
  signIn: 'sign-in',
  signUp: 'sign-up',
  isUserAuthorized: '/is-user-authorized/'
};

@Injectable()
export class UserService {

  private userApiBaseURL: string = environment.userServiceAPI;
  
  constructor(private http: HttpClient) { }

  public userSignIn(userInfo: any): Observable<any> {
    return this.http.post(this.userApiBaseURL + CONSTANTS.signIn, userInfo);
  }

  public userSignUp(registrationData: any): Observable<any> {
    return this.http.post(this.userApiBaseURL + CONSTANTS.signUp, registrationData);
  }
  
  public isUserAuthorized(userId: number): Observable<any> {
    return this.http.get(this.userApiBaseURL + CONSTANTS.isUserAuthorized + userId);
  }
}
