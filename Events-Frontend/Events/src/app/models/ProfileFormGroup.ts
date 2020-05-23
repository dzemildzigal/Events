import { FormGroup, FormControl, Validators } from "@angular/forms";
export class ProfileFormGroup extends FormGroup {
    constructor () {
        const urlReg = '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?';
        super({
            username: new FormControl(null, [Validators.required, Validators.minLength(3)]),
            password: new FormControl(null, [Validators.required, Validators.minLength(8)]),
            confirmPassword: new FormControl(null, [Validators.required, Validators.minLength(8)]),
            email: new FormControl(null, [Validators.required, Validators.minLength(3), Validators.email]),
            fullName: new FormControl(null, [Validators.required, Validators.minLength(3)]),
            profilePictureURL: new FormControl(null, [Validators.pattern(urlReg)])
          });
    }
}