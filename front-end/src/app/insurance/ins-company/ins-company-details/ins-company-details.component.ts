import {Component, OnDestroy, OnInit} from '@angular/core';
import {InsCompanyService} from "../ins-company.service";
import {ActivatedRoute} from "@angular/router";
import {InsCompanyDeatilsModel} from "../../../shared/models/ins-company/ins-company-deatils.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-ins-company-details',
  templateUrl: './ins-company-details.component.html',
  styleUrls: ['./ins-company-details.component.css']
})
export class InsCompanyDetailsComponent implements OnInit, OnDestroy {
  detailsModel!: InsCompanyDeatilsModel;
  observablesUnsubscribe: Subscription[] = [];
  constructor(
    private company: InsCompanyService,
    private activateRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    let id = null;
    this.activateRoute.params.subscribe(data => id = data['id']);
    let subscription = this.company.getOneById(id).subscribe(data => this.detailsModel = data[0]);
    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }


}
