import {Component, Input, OnDestroy, OnInit} from '@angular/core';
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
  filterIdentityNumber: string = "";
  @Input() productId: number;
  @Input() clientId: number;

  constructor(
    private policyService: PolicyService
  ) {
  }

  ngOnInit(): void {
    debugger
    if (this.productId) {
      let subscription = this.policyService.getAllByProductId(this.productId)
        .subscribe(data => {
          this.original = data;
          this.policyListModels = data;
        });
      this.observablesUnsubscribe.push(subscription);
    } else if (this.clientId) {
      let subscription = this.policyService.getAllByClientId(this.clientId)
        .subscribe(data => {
          this.original = data;
          this.policyListModels = data;
        });
      this.observablesUnsubscribe.push(subscription);
    } else {
      let subscription = this.policyService.getAll()
        .subscribe(data => {
          this.original = data;
          this.policyListModels = data;
        });
      this.observablesUnsubscribe.push(subscription);
    }

  }

  changeFilter() {
    this.policyListModels = this.original.filter(e => {
      return e.identityNumber.includes(this.filterIdentityNumber);
    })
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
