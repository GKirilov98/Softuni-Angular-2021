import {Injectable} from '@angular/core';
import InsCompanyAddModel from "../../shared/models/ins-company/ins-company-add.model";
import {Observable} from "rxjs";
import {HttpSenderService} from "../../shared/services/http-sender.service";
import {InsCompanyTableModel} from "../../shared/models/ins-company/ins-company-table.model";
import {InsCompanyDeatilsModel} from "../../shared/models/ins-company/ins-company-deatils.model";

@Injectable({
  providedIn: 'root'
})
export class InsCompanyService {

  constructor(
    private http: HttpSenderService
  ) {
  }

  insertOne(model: InsCompanyAddModel): Observable<any> {
    return this.http.post("InsCompany/", model);
  }

  getAll(): Observable<InsCompanyTableModel[]> {
   return  this.http.get("InsCompany/");
  }

  getOneById(id: number): Observable<InsCompanyDeatilsModel[]> {
    return this.http.get("InsCompany/" + id)
  }

  updateOne(id: number, model: InsCompanyAddModel) {
    return this.http.put("InsCompany/" + id, model);
  }

  deleteOneById(id: number): Observable<any>{
    return this.http.delete("InsCompany/" + id);
  }
}
