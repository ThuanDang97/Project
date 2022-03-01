import { Order } from "./order";
import { OrderService } from "./order-service";

export interface Payment {
    id: number,
    totalPayment: number,
    status: boolean
    orderServices: Array<OrderService>
    order: Order,
}
