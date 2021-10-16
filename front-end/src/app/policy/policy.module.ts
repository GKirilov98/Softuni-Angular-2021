import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PolicyCreateComponent} from "./policy-create/policy-create.component";
import {SharedModule} from "../shared/shared.module";



@NgModule({
  declarations: [
    PolicyCreateComponent
  ],
  imports: [
    SharedModule
  ]
})
export class PolicyModule { }
