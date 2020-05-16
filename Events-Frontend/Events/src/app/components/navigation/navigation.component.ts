import { Component, OnInit } from '@angular/core';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  public userDetails: any;

  constructor(private localStorageService: LocalStorageService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.userService.checkIsUserLoggedIn();
    this.localStorageService.userLoginChange.subscribe(details => {
      this.userDetails = details
    });
  }

  public logout(): void {
    this.localStorageService.setUserInfo(null);
  }
}
