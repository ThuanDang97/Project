export class Manufacturer {
  private manufacturerId!: number;
  private manufacturerName!: string;

  constructor(manufacturerId: number, manufacturerName: string) {
    this.manufacturerId = manufacturerId;
    this.manufacturerName = manufacturerName;
  }
}
