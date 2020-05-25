import { Component, OnInit } from '@angular/core';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { UserService } from 'src/app/services/user.service';
import { PopupService } from 'src/app/util/popup.service';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  public userDetails: any;
  public showAddEventComponent: boolean;
  constructor(private localStorageService: LocalStorageService,
              private userService: UserService,
              private popupService: PopupService,
              private eventService: EventService,
              ) { }

  ngOnInit(): void {
    this.userService.checkIsUserLoggedIn();
    this.localStorageService.userLoginChange.subscribe(details => {
      this.userDetails = details
    });
  }

  public addEvent(): any {
    this.popupService.addEventPopup().subscribe(dialogRes => {
      this.eventService.addEvent(dialogRes).subscribe(response => {
        console.log("New event added", response);
      })
    });
  }
  

  public logout(): void {
    this.localStorageService.setUserInfo(null);
  }
}
