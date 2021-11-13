import {Injectable} from '@angular/core';
import {PolicyCreateModel} from "../shared/models/policy/policy-create.model";
import {Observable} from "rxjs";
import {HttpSenderService} from "../shared/services/http-sender.service";
import {PolicyCalculationsModel} from "../shared/models/policy/policy-calculations.model";
import {PolicyListModel} from "../shared/models/policy/policy-list.model";
import {PolicyDetailsModel} from "../shared/models/policy/policy-details.model";

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

  getOneById(id: number): Observable<PolicyDetailsModel[]> {
    return this.http.get("Policy/" + id);
  }

  deleteOneById(id: number) {
    return this.http.delete("Policy/" + id);
  }

  getAllByProductId(id: number): Observable<PolicyListModel[]>{
    return this.http.get("Policy/getAllByProductId/" + id);
  }

  getAllByClientId(id: number): Observable<PolicyListModel[]>{
    return this.http.get("Policy/getAllByClientId/" + id);
  }
}
