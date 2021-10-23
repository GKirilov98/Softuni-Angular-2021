import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatIconModule} from "@angular/material/icon";
import {AppRoutesModule} from "../routes/app.routes.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpSenderService} from "./http-sender.service";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    NgbModule,
    BrowserAnimationsModule,
    MatIconModule,
    AppRoutesModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    CommonModule,
    NgbModule,
    BrowserAnimationsModule,
    MatIconModule,
    AppRoutesModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class SharedModule {
}
