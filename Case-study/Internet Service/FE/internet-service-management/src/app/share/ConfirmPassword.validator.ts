import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function validConfirmPassword(password:string,confirmPassword:string): ValidatorFn {
  return (control: AbstractControl): ValidationErrors => {
    if (control.get(password)?.value != control.get(confirmPassword)?.value) {
      return {"invalidPassword": true}
    }
    // @ts-ignore
    return null;
  };
}
