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
import {PolicyCreateComponent} from "../policy/policy-create/policy-create.component";
import {PolicyListComponent} from "../policy/policy-list/policy-list.component";
import {PolicyDetailsComponent} from "../policy/policy-details/policy-details.component";
import {ClientListComponent} from "../client/client-list/client-list.component";
import {UserListComponent} from "../admin/user-list/user-list.component";
import {InsProductDetailsComponent} from "../insurance/ins-product/ins-product-details/ins-product-details.component";
import {ClientDetailsComponent} from "../client/client-details/client-details.component";
import {NotFoun404Component} from "../error/not-foun404/not-foun404.component";
import {AuthGuard} from "../guard/AuthGuard";

const routes: Routes = [
  {path: "", component: HomeComponent, pathMatch: 'full'},
  {path: "home", component: HomeComponent, pathMatch: 'full'},
  {
    path: "user", children: [
      {path: "register", component: RegisterComponent, pathMatch: 'full'},
      {path: "login", component: LoginComponent, pathMatch: 'full'},
    ]
  },
  {
    path: "ins-company", children: [
      {
        path: "create",
        component: InsCompanyRegisterComponent,
        pathMatch: 'full',
        canActivate: [AuthGuard],
        data: {role: 'ADMIN'}
      },
      {
        path: "edit/:id",
        component: InsCompanyEditComponent,
        pathMatch: 'full',
        canActivate: [AuthGuard],
        data: {role: 'ADMIN'}
      },
      {path: "details/:id", component: InsCompanyDetailsComponent, pathMatch: 'full'},
      {path: "list", component: InsCompanyListComponent, pathMatch: 'full'},
    ]
  },
  {
    path: "ins-product", children: [
      {
        path: "create",
        component: InsProductCreateComponent,
        pathMatch: 'full',
        canActivate: [AuthGuard],
        data: {role: 'ADMIN'}
      },
      {
        path: "create/:id",
        component: InsProductCreateComponent,
        pathMatch: 'full',
        canActivate: [AuthGuard],
        data: {role: 'ADMIN'}
      },
      {
        path: "edit/:id",
        component: InsProductEditComponent,
        pathMatch: 'full',
        canActivate: [AuthGuard],
        data: {role: 'ADMIN'}
      },
      {path: "details/:id", component: InsProductDetailsComponent, pathMatch: 'full'},
      {path: "list", component: InsProductListComponent, pathMatch: 'full'},
    ]
  },
  {
    path: "policy", children: [
      {path: "create", component: PolicyCreateComponent, pathMatch: 'full'},
      {path: "list", component: PolicyListComponent, pathMatch: 'full'},
      {path: "details/:id", component: PolicyDetailsComponent, pathMatch: 'full'}
    ]
  },
  {
    path: "client", children: [
      {path: "list", component: ClientListComponent, pathMatch: 'full'},
      {path: "details/:id", component: ClientDetailsComponent, pathMatch: 'full'}
    ]
  },
  {
    path: "user", children: [
      {path: "list", component: UserListComponent, pathMatch: 'full'}
    ]
  },
  {path: "**", component: NotFoun404Component}
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
