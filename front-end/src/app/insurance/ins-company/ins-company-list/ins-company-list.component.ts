import {Component, OnDestroy, OnInit} from '@angular/core';
import {InsCompanyService} from "../ins-company.service";
import {InsCompanyTableModel} from "../../../shared/models/ins-company/ins-company-table.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-ins-company-list',
  templateUrl: './ins-company-list.component.html',
  styleUrls: ['./ins-company-list.component.css']
})
export class InsCompanyListComponent implements OnInit, OnDestroy {
  original: InsCompanyTableModel[];
  insCompanyTableModels: InsCompanyTableModel[];
  observablesUnsubscribe: Subscription[] = [];
  filterName:string = "";
  filterBulstat:string ="";

  constructor(
    private insCompanyService: InsCompanyService
  ) {
  }

  ngOnInit(): void {
    let subscription = this.insCompanyService.getAll()
      .subscribe(data => {
        this.original = data;
        this.insCompanyTableModels=data;
      });

    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }

  changeFilter() {
    this.insCompanyTableModels = this.original.filter(e => {
      return  e.name.includes(this.filterName) && e.bulstat.includes(this.filterBulstat);
    })
  }
}
