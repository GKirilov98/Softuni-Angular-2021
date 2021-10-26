export default class InsCompanyAddModel {
  public name: string;
  public bulstat: string;
  public address: string;
  public email: string;
  public phone: string;


  constructor(name: string, bulstat: string, address: string, email: string, phone: string) {
    this.name = name;
    this.bulstat = bulstat;
    this.address = address;
    this.email = email;
    this.phone = phone;
  }
}
