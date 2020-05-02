import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { LocalStorageService } from 'src/app/util/local-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;

  constructor(private userService: UserService,
              private localStorage: LocalStorageService) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      password: new FormControl(null, [Validators.required]),
      username: new FormControl(null, [Validators.required])
    });
  }

  public submit(): void {
    this.loginForm.updateValueAndValidity();
    if (this.loginForm.valid) {
      this.userService.userSignIn(this.loginForm.value).subscribe(result => {
         this.localStorage.setUserInfo(result);
      });
    }
  }

}
