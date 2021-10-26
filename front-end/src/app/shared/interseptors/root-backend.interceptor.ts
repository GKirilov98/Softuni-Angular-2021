import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {backEndRoot} from "../constants-fe";
import {NgxSpinnerService} from "ngx-spinner";

@Injectable()
export class RootBackendInterceptor implements HttpInterceptor {

  constructor(
    private spinner: NgxSpinnerService
  ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.spinner.show();
    let token = sessionStorage.getItem('token');
    const apiReq = request.clone({url: (backEndRoot + request.urlWithParams),
      setHeaders: {
        Authorization: `Bearer ${token}`
      }});
    return next.handle(apiReq);
  }
}
