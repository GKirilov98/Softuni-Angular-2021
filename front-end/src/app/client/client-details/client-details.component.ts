import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {ClientService} from "../client.service";
import ClientTableModel from "../../shared/models/client/client-table.model";
import {ClientDetailsModel} from "../../shared/models/client/client-details.model";

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit, OnDestroy {
  detailsModel!: ClientDetailsModel;
  observablesUnsubscribe: Subscription[] = [];


  constructor(
    private clientService: ClientService,
    private activateRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    let id = null;
    this.activateRoute.params.subscribe(data => id = data['id']);
    let subscription = this.clientService.getOneById(id).subscribe(data => {
      this.detailsModel = data[0];
    });
    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
