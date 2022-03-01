import {AbstractControl} from '@angular/forms';

export function DateBirthSearchValidator(control: AbstractControl): { [key: string]: boolean } | null {

  const fromDate = control.get('dateOfBirthFrom');
  const toDate = control.get('dateOfBirthTo');

  // @ts-ignore
  if (fromDate.value > toDate.value && fromDate.value != '' && toDate.value != '') {
    return {
      dateToSearch: true,
    };
  }

  // @ts-ignore
  if (toDate.value != '' && fromDate.value != '' && fromDate.value > toDate.value) {
    return {
      dateToSearch: true
    };
  }
  return {};
}
