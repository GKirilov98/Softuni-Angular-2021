import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Store} from "@ngrx/store";
import {IRootState} from "../+store";
import {Observable} from "rxjs";
import {catchError, map, tap} from "rxjs/operators";
import {IUser} from "../shared/interfaces/IUser";
import {login, register, logout, authenticate} from "../+store/actions/auth.action";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  currentUser$ = this.store.select((state) => state.auth.currUser)
  isReady$ = this.currentUser$.pipe(map(currentUser => currentUser !== null))
  isLogged$ = this.currentUser$.pipe(map(currentUser => currentUser !== undefined))

  constructor(private http: HttpClient,
              private store: Store<IRootState>) { }
  login(data:any): Observable<any>{
    return this.http.post(`/users/login`,data).pipe(
      tap((user: IUser)=> this.store.dispatch(login(user)))
    );
  }

  register(data:any): Observable<any>{
    return this.http.post(`/users/register`,data).pipe(
      tap((user: IUser)=> this.store.dispatch(register(user)))
    );
  }

  logout(): Observable<any>{
    return this.http.post(`/users/logout`,{}).pipe(
      tap((user: IUser)=> this.store.dispatch(logout(user)))
    );
  }

  autheticate(data:any): Observable<any>{
    return this.http.post(`/users/profile`,data).pipe(
      tap((user: IUser)=> this.store.dispatch(authenticate({user}))),
      catchError(()=>{
        this.store.dispatch(authenticate({user: null}));
        return [null];
      })
    );
  }
}
