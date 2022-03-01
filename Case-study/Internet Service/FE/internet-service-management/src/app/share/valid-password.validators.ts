import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function validPasswordValidators(password: string, confirmPass: string): ValidatorFn {
  return (control: AbstractControl): ValidationErrors => {
    if(control.get(password)?.value != control.get(confirmPass)?.value){
      return {'invalidPassword':true};
    }
    // @ts-ignore
    return null;
  }
}
