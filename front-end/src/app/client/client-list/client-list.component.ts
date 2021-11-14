import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {NotificationsService} from "../../shared/services/notifications.service";
import ClientTableModel from "../../shared/models/client/client-table.model";
import {ClientService} from "../client.service";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit, OnDestroy {
  sessionStorage = sessionStorage;
  original: ClientTableModel[];
  clientTableModels: ClientTableModel[];
  observablesUnsubscribe: Subscription[] = [];
  filterName: string = "";
  filterEgnBulstat: string = "";

  constructor(
    private clientService: ClientService,
    private notificationService: NotificationsService
  ) {
  }

  ngOnInit(): void {
    let subscription = this.clientService.getAll()
      .subscribe(data => {
        this.original = data;
        this.clientTableModels = data;
      });

    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }

  changeFilter() {
    this.clientTableModels = this.original.filter(e => {
      return e.name.includes(this.filterName) && e.egnBulstat.includes(this.filterEgnBulstat);
    })
  }

  deleteOne(id: number) {
    this.notificationService.confirmDanger("Сигурни ли сте, че искате да изтриете този обект!")
      .subscribe(data => {
        if (!data.Success) {
          let subscription = this.clientService.deleteOneById(id).subscribe(() => {
            this.notificationService.notifySuccess("Успешно изтрит клиент!");
            this.ngOnInit();
          });
          this.observablesUnsubscribe.push(subscription);
        }
      })
  }
}
