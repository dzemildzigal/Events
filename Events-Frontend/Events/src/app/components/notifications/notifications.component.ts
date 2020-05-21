import { Component, OnInit } from '@angular/core';
import { NotificationsService } from 'src/app/services/notifications.service';
import { LocalStorageService } from 'src/app/util/local-storage.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent implements OnInit {

  public notifications: any[];

  constructor(private notificationService: NotificationsService, 
              private localStorage: LocalStorageService) { }

  ngOnInit(): void {
    const userinfo = this.localStorage.getUserInfo();
    if(userinfo){
      this.notificationService.getNotificationsByUserId(userinfo.userId).subscribe(res => {
        this.notifications = res;
      });
    }
  }
}
