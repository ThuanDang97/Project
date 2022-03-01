import {IPosition} from "./IPosition";

export interface IEmployee {
  employeeId: string;
  fullName: string;
  dateOfBirth: string;
  email: string;
  address: string;
  phone: string;
  level: string;
  startWorkDate: string;
  yearOfExp: number;
  avtUrl: string;
  position: IPosition;
}
