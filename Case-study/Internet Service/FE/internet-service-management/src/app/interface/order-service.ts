import { Service } from "./service";

export interface OrderService {
    id: number,
    service: Service
    quantity: number,
    unit: number,
    totalMoney: number,
    order_date: string,
    status: boolean,
}
