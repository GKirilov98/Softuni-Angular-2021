import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserRegisterModel} from "./register/models/user-register.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient
  ) {
  }

  login(data: UserRegisterModel): Observable<any> {
    return this.http.post(`users/login`, data).pipe(
    );
  }

  register(data: UserRegisterModel) {
    return this.http.post('user/register', data)
      .subscribe(res => {
        console.log(res);
      });
  }

  //
  // logout(): Observable<any> {
  //   return this.http.post(`/users/logout`, {}).pipe(
  //     tap((user: UserModel) => this.store.dispatch(logout()))
  //   );
  // }
  //
  // autheticate(data: any): Observable<any> {
  //   return this.http.post(`/users/profile`, data).pipe(
  //     tap((user: UserModel) => this.store.dispatch(authenticate({user}))),
  //     catchError(() => {
  //       this.store.dispatch(authenticate({user: null}));
  //       return [null];
  //     })
  //   );
  // }
}
