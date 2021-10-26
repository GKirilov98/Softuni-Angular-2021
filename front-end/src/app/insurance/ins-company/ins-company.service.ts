import {Injectable} from '@angular/core';
import InsCompanyAddModel from "../../shared/models/ins-company/ins-company-add.model";
import {Observable} from "rxjs";
import {HttpSenderService} from "../../shared/http-sender.service";

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
}
