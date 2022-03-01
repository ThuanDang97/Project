import {IEmployeeCreate} from "./IEmployeeCreate";

export interface IPosition {
  positionId: number;
  positionName: string;
  employeeList?: IEmployeeCreate[];
}
