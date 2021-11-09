import {Injectable} from '@angular/core';
import {PolicyCreateModel} from "../shared/models/policy/policy-create.model";
import {Observable} from "rxjs";
import {HttpSenderService} from "../shared/services/http-sender.service";
import {PolicyCalculationsModel} from "../shared/models/policy/policy-calculations.model";
import {PolicyListModel} from "../shared/models/policy/policy-list.model";

@Injectable({
  providedIn: 'root'
})
export class PolicyService {

  constructor(
    private http: HttpSenderService
  ) {
  }

  insertOne(model: PolicyCreateModel): Observable<any> {
    return this.http.post("Policy/", model);
  }

  getCalculations(id: number, policySum: number): Observable<PolicyCalculationsModel[]> {
    return this.http.get("Policy/getCalculations?productId=" + id + "&sum=" + policySum);
  }

  getAll(): Observable<PolicyListModel[]> {
    return this.http.get("Policy/");
  }
}