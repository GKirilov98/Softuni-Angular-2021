import {Component, OnDestroy, OnInit} from '@angular/core';
import {InsProductService} from "../ins-product.service";
import {ActivatedRoute} from "@angular/router";
import {InsProductDetailsModel} from "../../../shared/models/ins-product/ins-product-details.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-ins-product-details',
  templateUrl: './ins-product-details.component.html',
  styleUrls: ['./ins-product-details.component.css']
})
export class InsProductDetailsComponent implements OnInit, OnDestroy {
  detailsModel!: InsProductDetailsModel;
  observablesUnsubscribe: Subscription[] = [];


  constructor(
    private productService: InsProductService,
    private activateRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    let id = null;
    this.activateRoute.params.subscribe(data => id = data['id']);
    let subscription = this.productService.getOneById(id).subscribe(data => {
      this.detailsModel = data[0];
    });
    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
