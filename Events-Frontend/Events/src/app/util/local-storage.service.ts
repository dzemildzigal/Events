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
  
  public setEvents(events:any): void {
    localStorage.setItem("events",JSON.stringify(events));
    this.eventChange.next(events);
  }
  public getEvents(): any {
    const output = localStorage.getItem("events");
    if(output) return JSON.parse(output);
    return null;
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

  public setAddEventTrigger(trigger: boolean): any {
    localStorage.setItem("addEventTrigger", JSON.stringify(trigger));
  }
  
  public getAddEventTrigger(): any {
    const result = localStorage.getItem("addEventTrigger");
    if(result){
      return JSON.parse(result);
    }
    return null;
  }

  public setEditEventTrigger(trigger: boolean): any {
    localStorage.setItem("editEventTrigger", JSON.stringify(trigger));
  }
  
  public editAddEventTrigger(): any {
    const result = localStorage.getItem("editEventTrigger");
    if(result){
      return JSON.parse(result);
    }
    return null;
  }
}
