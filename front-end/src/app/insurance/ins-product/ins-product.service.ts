import { Injectable } from '@angular/core';
import {InsProductCreateModel} from "../../shared/models/ins-product/ins-product-create.model";
import {HttpSenderService} from "../../shared/services/http-sender.service";
import {Observable} from "rxjs";
import {InsProductTableModel} from "../../shared/models/ins-product/ins-product-table.model";

@Injectable({
  providedIn: 'root'
})
export class InsProductService {

  constructor(
    private http: HttpSenderService
  ) { }

  insertOne(model: InsProductCreateModel): Observable<any> {
    return this.http.post("InsProduct/", model);
  }

  getAllByCompanyId(id: number): Observable<InsProductTableModel[]>{
    return this.http.get("InsProduct/getAllByCompanyId/" + id);
  }
}
