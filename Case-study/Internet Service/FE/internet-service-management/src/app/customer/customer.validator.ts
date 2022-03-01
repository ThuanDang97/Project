import {
  AbstractControl,
  ValidationErrors,
  ValidatorFn
} from '@angular/forms'


export function passwordConfirm(password:string, passwordRetype:string): ValidatorFn {
  return (control: AbstractControl): ValidationErrors => {
    if (control.get(password)?.value != control.get(passwordRetype)?.value) {
      return {"invalidPassword": true}
    }
    return null;
  };
}

export function ageValidator(min: number): ValidatorFn {
  return (control: AbstractControl): ValidationErrors => {
    let birthday = new Date(control.value);
    let age = (Date.now() - birthday.getTime()) / 31536000000;
    if (age < min) {
      return {"invalidAge": true}
    }
    // @ts-ignore
    return null;
  };
}
