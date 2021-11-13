import {NgModule} from '@angular/core';
import {InsCompanyRegisterComponent} from "./ins-company/ins-company-register/ins-company-register.component";
import {InsCompanyListComponent} from "./ins-company/ins-company-list/ins-company-list.component";
import {InsCompanyDetailsComponent} from "./ins-company/ins-company-details/ins-company-details.component";
import {InsCompanyEditComponent} from "./ins-company/ins-company-edit/ins-company-edit.component";
import {InsProductCreateComponent} from "./ins-product/ins-product-create/ins-product-create.component";
import {InsProductListComponent} from "./ins-product/ins-product-list/ins-product-list.component";
import {InsProductEditComponent} from "./ins-product/ins-product-edit/ins-product-edit.component";
import {InsTypeCreateComponent} from "./ins-type/ins-type-create/ins-type-create.component";
import {InsTypeListComponent} from "./ins-type/ins-type-list/ins-type-list.component";
import {InsTypeEditComponent} from "./ins-type/ins-type-edit/ins-type-edit.component";
import {SharedModule} from "../shared/shared.module";
import {InsProductDetailsComponent} from "./ins-product/ins-product-details/ins-product-details.component";
import {PolicyModule} from "../policy/policy.module";


@NgModule({
  declarations: [
    InsCompanyRegisterComponent,
    InsCompanyListComponent,
    InsCompanyDetailsComponent,
    InsCompanyEditComponent,
    InsProductCreateComponent,
    InsProductListComponent,
    InsProductEditComponent,
    InsProductDetailsComponent,
    InsTypeCreateComponent,
    InsTypeListComponent,
    InsTypeEditComponent
  ],
  imports: [
    SharedModule,
    PolicyModule
  ],
})
export class InsuranceModule { }
