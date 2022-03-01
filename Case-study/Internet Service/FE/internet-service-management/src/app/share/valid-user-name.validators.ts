import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function validUserNameValidators(check : boolean, userName: string): ValidatorFn {
  return (control: AbstractControl): ValidationErrors => {
    if (check) {
      return {'invalidUser': true};
    }
    // @ts-ignore
    return null;
  }
}
