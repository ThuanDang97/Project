import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function validExitsEmailValidator(check : boolean): ValidatorFn {
  return (control: AbstractControl): ValidationErrors => {
    if (check) {
      return {'invalidEmail': true};
    }
    // @ts-ignore
    return null;
  }
}
