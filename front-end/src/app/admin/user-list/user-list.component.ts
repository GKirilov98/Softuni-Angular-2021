import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserTableModel} from "../../shared/models/user/user-table.model";
import {AdminService} from "../admin.service";
import {NotificationsService} from "../../shared/services/notifications.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {
  filterUsername: string='';
  original: UserTableModel[];
  usersList: UserTableModel[];
  observablesUnsubscribe: Subscription[] = [];
  constructor(
    private adminService: AdminService,
    private notificationService: NotificationsService
  ) { }

  ngOnInit(): void {
    let subscription = this.adminService.findAll()
      .subscribe(data => {
        this.usersList = data;
        this.original = data;
      });
    this.observablesUnsubscribe.push(subscription);
  }

  changeFilter() {
    this.usersList = this.original.filter(e => {
      return  e.username.includes(this.filterUsername);
    })
  }

  changeRole(id: number, isAdmin: boolean) {
    if (isAdmin){
      this.notificationService.confirmDanger("Сигурни ли сте, че искате да премахнете правата на този потребител?")
        .subscribe(data => {
          if (!data.Success) {

            let subscription = this.adminService.addRemoveAdminRole(id).subscribe(() => {
              this.ngOnInit()
              this.notificationService.notifySuccess("Успешно премахнати права!");
            });
            this.observablesUnsubscribe.push(subscription);
          }
        })
    } else {
      this.notificationService.confirmDanger("Сигурни ли сте, че искате да дадете права на този потребител?")
        .subscribe(data => {
          if (!data.Success) {
            let subscription = this.adminService.addRemoveAdminRole(id).subscribe(() => {
              this.ngOnInit()
              this.notificationService.notifySuccess("Успешно добавени права!");
            });
            this.observablesUnsubscribe.push(subscription);
          }
        })
    }

  }

  deleteOne(id: number) {
    this.notificationService.confirmDanger("Сигурни ли сте, че искате да изтриете този потребител!")
      .subscribe(data => {
        if (!data.Success) {
          let subscription = this.adminService.deleteUserByUserId(id).subscribe(() => {
            this.ngOnInit()
            this.notificationService.notifySuccess("Успешно изтрит потребител!");
          });
          this.observablesUnsubscribe.push(subscription);
        }
      })
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
