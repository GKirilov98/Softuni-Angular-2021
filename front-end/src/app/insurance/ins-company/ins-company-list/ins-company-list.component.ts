import {Component, OnDestroy, OnInit} from '@angular/core';
import {InsCompanyService} from "../ins-company.service";
import {InsCompanyTableModel} from "../../../shared/models/ins-company/ins-company-table.model";
import {Subscription} from "rxjs";
import {NotificationsService} from "../../../shared/services/notifications.service";

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
    private insCompanyService: InsCompanyService,
    private notificationService: NotificationsService
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

  deleteOne(id: number) {
    this.notificationService.confirmDanger("Сигурни ли сте, че искате да изтриете този обект!")
      .subscribe(data => {
        if (!data.Success) {
          let subscription = this.insCompanyService.deleteOneById(id).subscribe(() =>{
            this.notificationService.notifySuccess("Успешно изтрита компания!");
            this.ngOnInit();
          } );
          this.observablesUnsubscribe.push(subscription);
        }
      })
  }
}
