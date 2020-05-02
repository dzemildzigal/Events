import { Injectable } from '@angular/core';

@Injectable()
export class LocalStorageService {

  constructor() { }


  public setUserInfo(userInfo: any): void {
    if (userInfo) {
      localStorage.setItem("userInfo", JSON.stringify(userInfo));
    }
  }

  public getUserInfo(): any {
    const info = localStorage.getItem("userInfo");
    if (info) {
      return JSON.parse(info);
    }
    return null;
  }
}
