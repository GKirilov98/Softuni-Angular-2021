import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {NotificationsService} from "../../../shared/services/notifications.service";
import {InsProductService} from "../ins-product.service";
import {InsProductTableModel} from "../../../shared/models/ins-product/ins-product-table.model";

@Component({
  selector: 'app-ins-product-list',
  templateUrl: './ins-product-list.component.html',
  styleUrls: ['./ins-product-list.component.css']
})
export class InsProductListComponent implements OnInit, OnDestroy {
  sessionStorage = sessionStorage
  original: InsProductTableModel[];
  productsTableModels: InsProductTableModel[];
  observablesUnsubscribe: Subscription[] = [];
  filterName: string = "";

  constructor(
    private insProductService: InsProductService,
    private notificationService: NotificationsService
  ) {
  }

  ngOnInit(): void {
    let subscription = this.insProductService.getAll()
      .subscribe(data => {
        this.original = data;
        this.productsTableModels = data;
      });

    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }

  changeFilter() {
    this.productsTableModels = this.original.filter(e => {
      return e.name.includes(this.filterName);
    })
  }

  deleteOne(id: number) {
    this.notificationService.confirmDanger("Сигурни ли сте, че искате да изтриете този обект!")
      .subscribe(data => {
        if (!data.Success) {
          let subscription = this.insProductService.deleteOneById(id).subscribe(() => {
            this.notificationService.notifySuccess("Успешно изтрита компания!");
            this.ngOnInit();
          });
          this.observablesUnsubscribe.push(subscription);
        }
      })
  }
}
