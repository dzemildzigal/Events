import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { ProfileFormGroup } from 'src/app/models/ProfileFormGroup';

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
    this.formGroup = new ProfileFormGroup();
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

  public buttonClick(event): void {
    this.onSubmit();
  }
}
