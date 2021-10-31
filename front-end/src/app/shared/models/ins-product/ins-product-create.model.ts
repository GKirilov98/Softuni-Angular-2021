export class InsProductCreateModel{
  public insTypeId: number;
  public insCompanyId: number;
  public name: string;
  public defered: boolean;
  public premium: number;
  public comission: number;


  constructor(insTypeId: number, insCompanyId: number, name: string, defered: boolean, premium: number, comission: number) {
    this.insTypeId = insTypeId;
    this.insCompanyId = insCompanyId;
    this.name = name;
    this.defered = defered;
    this.premium = premium;
    this.comission = comission;
  }
}
