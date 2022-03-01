import { Computer } from "./computer";
import { Customer } from "./customer";

export interface Order {
    id: number,
    customer: Customer,
    computer: Computer,
    startTime: string,
    endTime: string,
    usageTime: number
}
