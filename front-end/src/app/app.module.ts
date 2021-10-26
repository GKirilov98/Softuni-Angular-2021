import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HeaderComponent} from './common/header/header.component';
import {FooterComponent} from './common/footer/footer.component';
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {environment} from "../environments/environment";
import {AuthModule} from "./auth/auth.module";
import {HomeModule} from "./home/home.module";
import {InsuranceModule} from "./insurance/insurance.module";
import {PolicyModule} from "./policy/policy.module";
import {SharedModule} from "./shared/shared.module";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RootBackendInterceptor} from "./shared/interseptors/root-backend.interceptor";
import {NgxAwesomePopupModule, ToastNotificationConfigModule} from '@costlydeveloper/ngx-awesome-popup';
import {NotificationsService} from "./shared/notifications/notifications.service";
import {notificationsConfig} from "./shared/notifications/notifications.config";
import {NgxSpinnerModule} from "ngx-spinner";
import {HttpSenderService} from "./shared/http-sender.service";


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    (environment.production ? [] :
      StoreDevtoolsModule.instrument()),
    HttpClientModule,
    AuthModule,
    HomeModule,
    InsuranceModule,
    PolicyModule,
    NgxAwesomePopupModule.forRoot(), // Essential, mandatory main module.
    // DialogConfigModule.forRoot(), // Needed for instantiating dynamic components.
    // ConfirmBoxConfigModule.forRoot(), // Needed for instantiating confirm boxes.
    ToastNotificationConfigModule.forRoot(notificationsConfig),
    NgxSpinnerModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: RootBackendInterceptor, multi: true},
    NotificationsService, HttpSenderService
  ],
  bootstrap: [AppComponent]

})
export class AppModule {
}
