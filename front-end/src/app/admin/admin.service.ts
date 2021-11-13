import {Injectable} from '@angular/core';
import {HttpSenderService} from "../shared/services/http-sender.service";
import {Observable} from "rxjs";
import {UserTableModel} from "../shared/models/user/user-table.model";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(
    private http: HttpSenderService
  ) {
  }

  findAll(): Observable<UserTableModel[]> {
    return this.http.get("User/");
  }

  addRemoveAdminRole(id: number) {
    return this.http.post("User/addRemoveAdminRole/" + id);
  }

  deleteUserByUserId(id: number) {
    return this.http.delete("User/" + id)
  }
}
