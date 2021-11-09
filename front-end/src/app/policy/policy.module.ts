import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PolicyCreateComponent} from "./policy-create/policy-create.component";
import {SharedModule} from "../shared/shared.module";
import { PolicyListComponent } from './policy-list/policy-list.component';
import { PolicyDetailsComponent } from './policy-details/policy-details.component';



@NgModule({
  declarations: [
    PolicyCreateComponent,
    PolicyListComponent,
    PolicyDetailsComponent
  ],
  imports: [
    SharedModule
  ]
})
export class PolicyModule { }
