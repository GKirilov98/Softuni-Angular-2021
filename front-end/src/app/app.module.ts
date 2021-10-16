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
import {RootBackendInterceptor} from "./shared/root-backend.interceptor";


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
    PolicyModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: RootBackendInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]

})
export class AppModule {
}
