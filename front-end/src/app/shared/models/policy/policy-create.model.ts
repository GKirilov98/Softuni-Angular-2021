export class PolicyCreateModel{
  public isLegal: boolean;
  public isNew: boolean;
  public name: string;
  public middleName:string;
  public lastName:string;
  public egn: string;
  public bulstat:string;
  public email:string;
  public phoneNumber: string;
  public address: string;
  public note: string;
  public identityNumber: string;
  public polisySum: number;
  public insProductId: number;
  public insObjectName: string;
  public insObjectTypeId: number;
  public insObjectDescription: string;
  public policyNote: string;
  public startDate: Date;
  public endDate: Date;


  constructor(isLegal: boolean, isNew: boolean, name: string, middleName: string, lastName: string, egn: string, bulstat: string, email: string, phoneNumber: string, address: string, note: string, identityNumber: string, polisySum: number, insProductId: number, insObjectName: string, insObjectTypeId: number, insObjectDescription: string, policyNote: string, startDate: Date, endDate: Date) {
    this.isLegal = isLegal;
    this.isNew = isNew;
    this.name = name;
    this.middleName = middleName;
    this.lastName = lastName;
    this.egn = egn;
    this.bulstat = bulstat;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.note = note;
    this.identityNumber = identityNumber;
    this.polisySum = polisySum;
    this.insProductId = insProductId;
    this.insObjectName = insObjectName;
    this.insObjectTypeId = insObjectTypeId;
    this.insObjectDescription = insObjectDescription;
    this.policyNote = policyNote;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
