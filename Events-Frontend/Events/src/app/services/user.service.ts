import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { LocalStorageService } from '../util/local-storage.service';

const CONSTANTS: any = {
  signIn: 'sign-in',
  signUp: 'user-info/sign-up',
  isUserAuthorized: 'is-user-authorized/'
};

@Injectable()
export class UserService {

  private userApiBaseURL: string = environment.userServiceAPI;
  
  constructor(private http: HttpClient,
              private localStorage: LocalStorageService) {
               }

  public userSignIn(userInfo: any): Observable<any> {
    return this.http.post(this.userApiBaseURL + CONSTANTS.signIn, userInfo);
  }

  public userSignUp(registrationData: any): Observable<any> {
    return this.http.post(this.userApiBaseURL + CONSTANTS.signUp, registrationData);
  }
  
  public isUserAuthorized(userId: number): Observable<any> {
    return this.http.get(this.userApiBaseURL + CONSTANTS.isUserAuthorized + userId);
  }

  public checkIsUserLoggedIn(): void {
    const userInfo: any = this.localStorage.getUserInfo();
    if (userInfo) {
      this.isUserAuthorized(userInfo.userId).subscribe(res => {
        if (res.authenticated) {
          this.localStorage.setUserInfo(userInfo);
        }
      });
    }
    
  }
}
