import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

const CONSTANTS: any = {
  incorrect : "incorrect"
}
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  formGroup: FormGroup;

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.formGroup = new FormGroup (
      {
        username: new FormControl(null, [Validators.required, Validators.minLength(3)]),
        password: new FormControl(null, [Validators.required, Validators.minLength(8)]),
        confirmPassword: new FormControl(null, [Validators.required, Validators.minLength(8)]),
        email: new FormControl(null, [Validators.required, Validators.minLength(3), Validators.email]),
        fullName: new FormControl(null, [Validators.required, Validators.minLength(3)]),
        profilePictureURL: new FormControl(null)
      }
    )  
  }

  public onSubmit(): void {
    if (this.formGroup.valid) {
      const formGroupValue = this.formGroup.value;
      const userCredentialsDTO = {
        username: formGroupValue.username,
        password: formGroupValue.password,
        user: formGroupValue
      };
      this.userService.userSignUp(userCredentialsDTO).subscribe(res => {
        if (res) {
          this.router.navigateByUrl("/login")
        }
      });
    }
  }

  public confirmPasswordChange(): void {
    if (this.formGroup.value.password != this.formGroup.value.confirmPassword) {
      this.formGroup.controls.confirmPassword.setErrors({'incorrect': true});
      return;
    }
    this.formGroup.controls.confirmPassword.setErrors(null);
  }
}
