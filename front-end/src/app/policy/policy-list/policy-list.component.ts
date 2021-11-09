import {Component, OnDestroy, OnInit} from '@angular/core';
import {InsCompanyTableModel} from "../../shared/models/ins-company/ins-company-table.model";
import {Subscription} from "rxjs";
import {PolicyListModel} from "../../shared/models/policy/policy-list.model";
import {PolicyService} from "../policy.service";

@Component({
  selector: 'app-policy-list',
  templateUrl: './policy-list.component.html',
  styleUrls: ['./policy-list.component.css']
})
export class PolicyListComponent implements OnInit, OnDestroy {
  original: PolicyListModel[];
  policyListModels: PolicyListModel[];
  observablesUnsubscribe: Subscription[] = [];
  filterIdentityNumber:string = "";

  constructor(
    private policyService: PolicyService
  ) { }

  ngOnInit(): void {
    let subscription = this.policyService.getAll()
      .subscribe(data => {
        this.original = data;
        this.policyListModels=data;
      });
    this.observablesUnsubscribe.push(subscription);
  }

  changeFilter() {
    this.policyListModels = this.original.filter(e => {
      return  e.identityNumber.includes(this.filterIdentityNumber);
    })
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
