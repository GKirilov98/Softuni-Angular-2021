import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import CarouselComponent from "./home/carousel/carousel.component";
import {HeaderComponent} from './common/header/header.component';
import {FooterComponent} from './common/footer/footer.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import {LoginComponent} from './auth/login/login.component';
import {RegisterComponent} from './auth/register/register.component';
import {InsCompanyRegisterComponent} from './insurance/ins-company/ins-company-register/ins-company-register.component';
import {ReactiveFormsModule} from "@angular/forms";
import {InsCompanyListComponent} from './insurance/ins-company/ins-company-list/ins-company-list.component';
import {InsCompanyDetailsComponent} from './insurance/ins-company/ins-company-details/ins-company-details.component';
import {InsCompanyEditComponent} from './insurance/ins-company/ins-company-edit/ins-company-edit.component';
import {InsProductCreateComponent} from './insurance/ins-product/ins-product-create/ins-product-create.component';
import {InsProductListComponent} from './insurance/ins-product/ins-product-list/ins-product-list.component';
import {InsProductEditComponent} from './insurance/ins-product/ins-product-edit/ins-product-edit.component';
import {InsTypeCreateComponent} from './insurance/ins-type/ins-type-create/ins-type-create.component';
import {InsTypeListComponent} from './insurance/ins-type/ins-type-list/ins-type-list.component';
import {InsTypeEditComponent} from './insurance/ins-type/ins-type-edit/ins-type-edit.component';
import {PolicyCreateComponent} from './policy/policy-create/policy-create.component';
import {RouterModule} from "@angular/router";
import {AppRoutesModule} from "./routes/app.routes.module";


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CarouselComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    InsCompanyRegisterComponent,
    InsCompanyListComponent,
    InsCompanyDetailsComponent,
    InsCompanyEditComponent,
    InsProductCreateComponent,
    InsProductListComponent,
    InsProductEditComponent,
    InsTypeCreateComponent,
    InsTypeListComponent,
    InsTypeEditComponent,
    PolicyCreateComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    BrowserAnimationsModule,
    MatIconModule,
    ReactiveFormsModule,
    AppRoutesModule
  ],
  providers: [],
  bootstrap: [AppComponent]

})
export class AppModule {
}
