import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class LocalStorageService {
 
  public userLoginChange: Subject<any>;
  public eventChange : Subject<any>;

  constructor() { 
    this.userLoginChange = new Subject<any>();
    this.eventChange = new Subject<any>();
  }

  public setUserInfo(userInfo: any): void {
    localStorage.setItem("userInfo", JSON.stringify(userInfo));
    this.userLoginChange.next(userInfo);
  }

  public getUserInfo(): any {
    const info = localStorage.getItem("userInfo");
    if (info) {
      return JSON.parse(info);
    }
    return null;
  }
}
