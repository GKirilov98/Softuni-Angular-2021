import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
  RouterStateSnapshot,
  UrlTree
} from "@angular/router";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {NotificationsService} from "../shared/services/notifications.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild {


  constructor(
    private router: Router,
    private notificationService: NotificationsService,
  ) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let url: string = state.url;
    return this.checkUserLogin(next, url);
  }

  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.canActivate(next, state);
  }

  checkUserLogin(route: ActivatedRouteSnapshot, url: any): boolean {
    if ( sessionStorage.getItem("username")) {
      const userRole = sessionStorage.getItem("roles");
      if (route.data.role && !userRole.includes(route.data.role)) {
        this.notificationService.notifyError("Нямате необходимите права за достап!")
        this.router.navigate(['/home']).then();
        return false;
      }
      return true;
    }

    this.router.navigate(['/home']).then();
    return false;
  }
}
