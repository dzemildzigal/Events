import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ProfileFormGroup } from 'src/app/models/ProfileFormGroup';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent implements OnInit {

  @Input()
  public buttonName: string;

  @Input()
  public formGroup: ProfileFormGroup;

  @Output()
  public buttonClick = new EventEmitter<any>();

  @Input()
  public disableUsername = false;

  constructor() { }

  ngOnInit(): void {
    console.log(this.disableUsername);
  }

  public confirmPasswordChange(): void {
    if (this.formGroup.value.password != this.formGroup.value.confirmPassword) {
      this.formGroup.controls.confirmPassword.setErrors({'incorrect': true});
      return;
    }
    this.formGroup.controls.confirmPassword.setErrors(null);
  }

  public onSubmit() {
    this.buttonClick.emit(null);
  }
}
