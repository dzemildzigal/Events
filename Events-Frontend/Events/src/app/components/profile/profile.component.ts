import { Component, OnInit } from '@angular/core';
import { ProfileFormGroup } from 'src/app/models/ProfileFormGroup';
import { UserService } from 'src/app/services/user.service';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public formGroup: ProfileFormGroup;
  private userInfo: any;

  constructor(private userService: UserService,
              private localStorageService: LocalStorageService,
              private router: Router) { }

  ngOnInit(): void {
    this.formGroup = new ProfileFormGroup();
    this.userInfo = this.localStorageService.getUserInfo();

    this.userService.getUserInfo(this.userInfo.userId).subscribe(res => {
      console.log(res);
      const formValue: any = Object.assign(res, {
        username: this.userInfo.username
      });
      this.formGroup.patchValue(formValue);
    });

   
  }

  public buttonClick(event): void {
    if (this.formGroup.valid) {
      const formGroupValue = Object.assign(this.formGroup.value, { userId: this.userInfo.userId });
      const userCredentialsDTO = {
        username: formGroupValue.username,
        password: formGroupValue.password,
        user: formGroupValue,
        userCredentialsId: this.userInfo.userCredentialsId
      };
      this.userService.updateUserInfo(userCredentialsDTO).subscribe(res => {
        if (res) {
          this.router.navigateByUrl("/")
        }
      });
    }
  }

  public removeProfile(): void {
    this.userService.deleteUser(this.userInfo.userId).subscribe(res => {
      this.localStorageService.setUserInfo(null);
      this.router.navigateByUrl("/");
    });
  }
}
