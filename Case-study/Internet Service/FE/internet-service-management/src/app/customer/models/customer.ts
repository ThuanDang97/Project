import {Account} from "./account";

export interface Customer {
  customer_id: number;
  address: string;
  date_of_birth: string;
  email: string;
  full_name: string;
  id_card: string;
  phone: string;
  status: number;
  user_name: Account;
}
