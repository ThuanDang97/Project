export class CustomerRegister {
  private customerId !: number;
  private fullName !: string;
  private dateOfBirth !: string;
  private email !: string;
  private address !: string;
  private phone !: string;
  private status !: boolean;
  private idCard !: string;
  private userName !: string;
  private password !: string;


  constructor(customerId: number, fullName: string, dateOfBirth: string, email: string, address: string, phone: string, status: boolean, idCard: string, userName: string, password: string) {
    this.customerId = customerId;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.address = address;
    this.phone = phone;
    this.status = status;
    this.idCard = idCard;
    this.userName = userName;
    this.password = password;
  }

}
