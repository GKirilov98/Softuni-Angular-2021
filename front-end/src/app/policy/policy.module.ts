import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PolicyCreateComponent} from "./policy-create/policy-create.component";
import {SharedModule} from "../shared/shared.module";
import { PolicyListComponent } from './policy-list/policy-list.component';



@NgModule({
  declarations: [
    PolicyCreateComponent,
    PolicyListComponent
  ],
  imports: [
    SharedModule
  ]
})
export class PolicyModule { }
