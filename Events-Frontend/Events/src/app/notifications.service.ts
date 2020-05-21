import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocalStorageService } from './util/local-storage.service';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class NotificationsService {
  
  private notificationApiBaseURL: string = environment.notificationServiceAPI;
  constructor(private httpClient: HttpClient,
    private localStorage: LocalStorageService) {

     }
     getNotificationsByUserId(id: any): Observable<any> {

      let url = this.notificationApiBaseURL + 'notifications/get-notifications/' + id.toString();

      return this.httpClient.get<any>(url).pipe(map(res => {return res}));
     }
}
