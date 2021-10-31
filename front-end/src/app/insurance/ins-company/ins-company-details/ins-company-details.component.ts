import {Component, OnDestroy, OnInit} from '@angular/core';
import {InsCompanyService} from "../ins-company.service";
import {ActivatedRoute} from "@angular/router";
import {InsCompanyDeatilsModel} from "../../../shared/models/ins-company/ins-company-deatils.model";
import {Subscription} from "rxjs";
import {InsProductTableModel} from "../../../shared/models/ins-product/ins-product-table.model";
import {InsProductService} from "../../ins-product/ins-product.service";

@Component({
  selector: 'app-ins-company-details',
  templateUrl: './ins-company-details.component.html',
  styleUrls: ['./ins-company-details.component.css']
})
export class InsCompanyDetailsComponent implements OnInit, OnDestroy {
  detailsModel!: InsCompanyDeatilsModel;
  products!: InsProductTableModel[];
  originalProduct!: InsProductTableModel[];
  observablesUnsubscribe: Subscription[] = [];
  filterName: string;
  constructor(
    private company: InsCompanyService,
    private insProductService: InsProductService,
    private activateRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    let id = null;
    this.activateRoute.params.subscribe(data => id = data['id']);
    let subscription = this.company.getOneById(id).subscribe(data => this.detailsModel = data[0]);
    this.observablesUnsubscribe.push(subscription);
    let subscribe = this.insProductService.getAllByCompanyId(id).subscribe(
      data => {
        this.products = data;
        this.originalProduct = data;
      }
    );
    this.observablesUnsubscribe.push(subscribe);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }


  changeFilter() {
    this.products = this.originalProduct.filter(e => {
      return  e.name.includes(this.filterName);
    })
  }
}
