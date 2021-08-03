import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import CarouselComponent from "./home/carousel/carousel.component";
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { InsProductComponent } from './insurance/ins-product/ins-product.component';
import { InsTypeComponent } from './insurance/ins-type/ins-type.component';
import { InsCompanyRegisterComponent } from './insurance/ins-company/ins-company-register/ins-company-register.component';
import {ReactiveFormsModule} from "@angular/forms";
import { InsCompanyListComponent } from './insurance/ins-company/ins-company-list/ins-company-list.component';
import { InsCompanyDetailsComponent } from './insurance/ins-company/ins-company-details/ins-company-details.component';
import { InsCompanyEditComponent } from './insurance/ins-company/ins-company-edit/ins-company-edit.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CarouselComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    InsProductComponent,
    InsTypeComponent,
    InsCompanyRegisterComponent,
    InsCompanyListComponent,
    InsCompanyDetailsComponent,
    InsCompanyEditComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    BrowserAnimationsModule,
    MatIconModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]

})
export class AppModule { }
