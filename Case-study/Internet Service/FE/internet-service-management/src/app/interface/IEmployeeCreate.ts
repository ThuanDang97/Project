
export interface IEmployeeCreate {
  employeeId: string,
  fullName: string,
  dateOfBirth: string,
  email: string,
  address: string,
  phone: string,
  level: string,
  startWorkDate: string,
  yearOfExp: number,
  avtUrl: string,
  userName: string,
  positionId: number;
  password: string,
  msgStartWorkDate?: string;
  msgDateOfBirth?: string;
  msgCode?: string;
  msgEmail?: string;
  status?: boolean;
  msgPassword?: string;

}
