import { Injectable } from '@angular/core';
import {HttpSenderService} from "./http-sender.service";
import NomenclatureModel from "../models/nomenclature/nomenclature.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NomenclatureService {

  constructor(
    private http: HttpSenderService
  ) { }

  getAllNInsTypes(): Observable<NomenclatureModel[]>{
    return this.http.get("NInsType/")
  }
}
