import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;
  public errorMessage: string;
  
  constructor(private userService: UserService,
              private localStorage: LocalStorageService,
              private router: Router) { }

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
        if (result.authenticated) {
          this.localStorage.setUserInfo(result);
          this.router.navigateByUrl("/");
          return;
        }
        this.errorMessage = "User with provided credentials doesn't exist";
      });
    }
  }

}
