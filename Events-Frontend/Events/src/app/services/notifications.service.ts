import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable()
export class NotificationsService {
  
  private notificationApiBaseURL: string = environment.notificationServiceAPI;
  
  constructor(private httpClient: HttpClient) {}
  
  public getNotificationsByUserId(id: any): Observable<any> {
    let url = this.notificationApiBaseURL + 'notifications/get-notifications/' + id.toString();
    return this.httpClient.get<any>(url);
  }
  
  public subscribeToEvent(subscription: any): Observable<any>{
   // console.log(subscription);
   console.log(subscription);
    let url = this.notificationApiBaseURL + 'notifications/newsubscription';
    return this.httpClient.post<any>(url, subscription);
   
  }

}
