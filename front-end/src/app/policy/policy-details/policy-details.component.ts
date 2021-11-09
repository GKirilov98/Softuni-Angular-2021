import {Component, OnDestroy, OnInit} from '@angular/core';
import {PolicyDetailsModel} from "../../shared/models/policy/policy-details.model";
import {PolicyService} from "../policy.service";
import {ActivatedRoute} from "@angular/router";
import {NotificationsService} from "../../shared/services/notifications.service";
import {Subscription} from "rxjs";
import { Location } from '@angular/common'

@Component({
  selector: 'app-policy-details',
  templateUrl: './policy-details.component.html',
  styleUrls: ['./policy-details.component.css']
})
export class PolicyDetailsComponent implements OnInit, OnDestroy {
  policy: PolicyDetailsModel
  observablesUnsubscribe: Subscription[] = [];
  constructor(
    private policyService: PolicyService,
    private activateRoute: ActivatedRoute,
    private notificationService: NotificationsService,
    private location: Location
  ) { }

  ngOnInit(): void {
    let id = null;
    this.activateRoute.params.subscribe(data => id = data['id']);
    let subscription = this.policyService.getOneById(id).subscribe(data => {
      this.policy = data[0];
    });
    this.observablesUnsubscribe.push(subscription)
  }

  deleteOne(id: number) {
    this.notificationService.confirmDanger("Сигурни ли сте, че искате да изтриете тази полица!")
      .subscribe(data => {
        if (!data.Success) {
          let subscription = this.policyService.deleteOneById(id).subscribe(() => {
            this.notificationService.notifySuccess("Успешно изтрита полица!");
            this.location.back()
          });
          this.observablesUnsubscribe.push(subscription);
        }
      })
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }

}
