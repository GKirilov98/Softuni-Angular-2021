import {Injectable, OnDestroy} from '@angular/core';
import {Observable} from "rxjs";
import {UserRegisterModel} from "../shared/models/register/user-register.model";
import {HttpSenderService} from "../shared/services/http-sender.service";
import {UserModel} from "../shared/models/user/user.model";
import {map} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class AuthService  {
  constructor(
    private http: HttpSenderService
  ) {
  }

  login(data: UserModel): Observable<any> {
    return this.http.post(`user/login`, data)
      .pipe(map(data => {
        sessionStorage.setItem("token", data[0].token);
        sessionStorage.setItem("username", data[0].username);
        sessionStorage.setItem("roles", JSON.stringify(data[0].roles));
        if (data[0].roles.includes('ADMIN')) {
          sessionStorage.setItem('isAdmin', 'true');
        }
        return data;
      }));
  }

  register(data: UserRegisterModel) {
    return this.http.post('user/register', data);
  }
}
