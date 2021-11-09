import { Component, OnInit } from '@angular/core';
import {PolicyDetailsModel} from "../../shared/models/policy/policy-details.model";
import {PolicyService} from "../policy.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-policy-details',
  templateUrl: './policy-details.component.html',
  styleUrls: ['./policy-details.component.css']
})
export class PolicyDetailsComponent implements OnInit {
  policy: PolicyDetailsModel
  constructor(
    private policyService: PolicyService,
    private activateRoute: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    let id = null;
    this.activateRoute.params.subscribe(data => id = data['id']);
    this.policyService.getOneById(id).subscribe(data => {
      this.policy = data[0];
        console.log(this.policy);
    });
  }

}
