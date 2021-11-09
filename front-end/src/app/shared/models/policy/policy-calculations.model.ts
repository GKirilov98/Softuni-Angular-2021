export class PolicyCalculationsModel{
  public  premium: number;
  public  tax: number;
  public  commission: number;

  constructor(premium: number, tax: number, commission: number) {
    this.premium = premium;
    this.tax = tax;
    this.commission = commission;
  }
}
