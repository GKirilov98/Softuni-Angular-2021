import {HttpClient} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";
import {NotificationsService} from "./notifications/notifications.service";
import {NgxSpinnerService} from "ngx-spinner";
import {Observable, of, throwError} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class HttpSenderService {

  constructor(
    private http: HttpClient,
    private notifications: NotificationsService,
    private spinner: NgxSpinnerService
  ) {
  }

  post(url: string, data: any): Observable<any> {
    return this.http.post(url, data)
      .pipe(
        tap(data => {
          this.spinner.hide();
        }),
        catchError(this.handleError.bind(this))
      )
    // .subscribe(res => {
    //   // this.notifier.notify('success', "Успешно регистриран!");
    // });
  }

  handleError(err: any) {
    this.notifications.notifyError(err.error.message);
    this.spinner.hide();
    return throwError(err);
  }
}
