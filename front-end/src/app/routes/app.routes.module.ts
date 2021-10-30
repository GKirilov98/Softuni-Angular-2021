import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "../home/home.component";
import {RegisterComponent} from "../auth/register/register.component";
import {LoginComponent} from "../auth/login/login.component";
import {InsCompanyRegisterComponent} from "../insurance/ins-company/ins-company-register/ins-company-register.component";
import {InsCompanyEditComponent} from "../insurance/ins-company/ins-company-edit/ins-company-edit.component";
import {InsCompanyDetailsComponent} from "../insurance/ins-company/ins-company-details/ins-company-details.component";
import {InsCompanyListComponent} from "../insurance/ins-company/ins-company-list/ins-company-list.component";
import {InsProductCreateComponent} from "../insurance/ins-product/ins-product-create/ins-product-create.component";
import {InsProductEditComponent} from "../insurance/ins-product/ins-product-edit/ins-product-edit.component";
import {InsProductListComponent} from "../insurance/ins-product/ins-product-list/ins-product-list.component";
import {InsTypeCreateComponent} from "../insurance/ins-type/ins-type-create/ins-type-create.component";
import {InsTypeEditComponent} from "../insurance/ins-type/ins-type-edit/ins-type-edit.component";
import {InsTypeListComponent} from "../insurance/ins-type/ins-type-list/ins-type-list.component";
import {PolicyCreateComponent} from "../policy/policy-create/policy-create.component";

const routes: Routes = [
  {path: "", component: HomeComponent, pathMatch: 'full'},
  {path: "home", component: HomeComponent, pathMatch: 'full'},
  {path: "user", children:[
      {path:"register", component: RegisterComponent, pathMatch:'full' },
      {path:"login", component: LoginComponent, pathMatch:'full' },
    ]},
  {path: "ins-company", children:[
      {path:"create", component: InsCompanyRegisterComponent, pathMatch:'full' },
      {path:"edit/:id", component: InsCompanyEditComponent, pathMatch:'full' },
      {path:"details/:id", component: InsCompanyDetailsComponent, pathMatch:'full' },
      {path:"list", component: InsCompanyListComponent, pathMatch:'full' },
    ]},
  {path: "ins-product", children:[
      {path:"create", component: InsProductCreateComponent, pathMatch:'full' },
      {path:"create/:id", component: InsProductCreateComponent, pathMatch:'full' },
      {path:"edit/:id", component: InsProductEditComponent, pathMatch:'full' },
      {path:"list", component: InsProductListComponent, pathMatch:'full' },
    ]},
  {path: "ins-type", children:[
      {path:"create", component: InsTypeCreateComponent, pathMatch:'full' },
      {path:"edit/:id", component: InsTypeEditComponent, pathMatch:'full' },
      {path:"list", component: InsTypeListComponent, pathMatch:'full' },
    ]},
  {path: "policy", children:[
      {path:"create", component: PolicyCreateComponent, pathMatch:'full' },
      // {path:"edit/:id", component: InsTypeEditComponent, pathMatch:'full' },
      // {path:"list", component: InsTypeListComponent, pathMatch:'full' },
    ]},
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutesModule {
}
