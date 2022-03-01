import {IManufacturer} from "./IManufacturer";
import {IStatus} from "./IStatus";
import {IType} from "./IType";

export interface IComputer {
  computerId: string,
  computerLocation: string,
  computerStartUsedDate: string,
  computerWarrantyPeriod: string,
  computerConfiguration: string,
  computerIpLocal: string,
  manufacturer: IManufacturer,
  status: IStatus,
  type: IType,

}
