import {IAccount} from "./IAccount";
import {IPosition} from "./IPosition";

export class Employee {
  private employeeId!: string;
  private fullName!: string;
  private dateOfBirth!: string;
  private email!: string;
  private address!: string;
  private phone!: string;
  private level!: string;
  private startWorkDate!: string;
  private yearOfExp!: number;
  private avtUrl!: string;
  private userName!: string;
  private positionId!: number;
  private password!: string;


  constructor(employeeId: string, fullName: string, dateOfBirth: string, email: string, address: string, phone: string, level: string, startWorkDate: string, yearOfExp: number, avtUrl: string, positionId: number, userName: string, password: string) {
    this.employeeId = employeeId;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.address = address;
    this.phone = phone;
    this.level = level;
    this.startWorkDate = startWorkDate;
    this.yearOfExp = yearOfExp;
    this.avtUrl = avtUrl;
    this.positionId = positionId;
    this.userName = userName;
    this.password = password;
  }
}
