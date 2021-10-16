import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomeComponent} from "./home.component";
import CarouselComponent from "./carousel/carousel.component";
import {SharedModule} from "../shared/shared.module";



@NgModule({
  declarations: [
    HomeComponent,
    CarouselComponent,
  ],
  imports: [
    CommonModule,
    SharedModule
  ]
})
export class HomeModule { }
