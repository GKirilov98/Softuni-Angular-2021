import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {NotificationsService} from "../../../shared/services/notifications.service";
import {ActivatedRoute, Router} from "@angular/router";
import {InsProductService} from "../ins-product.service";
import NomenclatureModel from "../../../shared/models/nomenclature/nomenclature.model";
import {NomenclatureService} from "../../../shared/services/nomenclature.service";
import {InsCompanyDropdownModel} from "../../../shared/models/ins-company/ins-company-dropdown.model";
import {InsCompanyService} from "../../ins-company/ins-company.service";
import {InsProductCreateModel} from "../../../shared/models/ins-product/ins-product-create.model";

@Component({
  selector: 'app-ins-product-create',
  templateUrl: './ins-product-create.component.html',
  styleUrls: ['../../../shared/css/forms.component.css']
})
export class InsProductCreateComponent implements OnInit, OnDestroy {
  nInsTypes!: NomenclatureModel[];
  insCompany!: InsCompanyDropdownModel[];
  registerForm!: FormGroup;
  observablesUnsubscribe: Subscription[] = [];

  constructor(
    private insProductService: InsProductService,
    private nomenclatureService: NomenclatureService,
    private notificationService: NotificationsService,
    private router: Router,
    private insCompanyService: InsCompanyService,
    private activateRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    let subscription = this.nomenclatureService.getAllNInsTypes()
      .subscribe(data => this.nInsTypes = data);
    this.observablesUnsubscribe.push(subscription)
    let id = undefined;
    this.activateRoute.params.subscribe(data => id = data['id']);
    if (id) {
      let subscription1 = this.insCompanyService.getOneById(id).subscribe(data => {
        // @ts-ignore
        this.insCompany = data
      });
      this.observablesUnsubscribe.push(subscription1);
    } else {
      let subscription1 = this.insCompanyService.getAll().subscribe(data => {
        // @ts-ignore
        this.insCompany = data
      });
      this.observablesUnsubscribe.push(subscription1);
    }

    this.initForm();
  }

  private initForm() {
    this.registerForm = new FormGroup({
      'insTypeId': new FormControl('', [Validators.required]),
      'insCompanyId': new FormControl('', [Validators.required]),
      'name': new FormControl(null,
        [Validators.required, Validators.minLength(3), Validators.maxLength(50)]
      ),
      'defered': new FormControl(null, [Validators.required, Validators.min(0)]),
      'premium': new FormControl(null, [Validators.required, Validators.min(0)]),
      'comission': new FormControl(null, [Validators.required, Validators.min(0)]),
    });
  }

  onSubmit() {
    let companyId = this.registerForm.get('insCompanyId').value;
    let model = new InsProductCreateModel(
      this.registerForm.get('insTypeId').value,
      companyId,
      this.registerForm.get('name').value,
      this.registerForm.get('defered').value,
      this.registerForm.get('premium').value,
      this.registerForm.get('comission').value
    )
    let subscription = this.insProductService.insertOne(model)
      .subscribe(data => {
        this.notificationService.notifySuccess("Успешно добавен продукт!");
        this.router.navigate(["/ins-company/details", companyId]).then();
      });
    this.observablesUnsubscribe.push(subscription);
  }

  ngOnDestroy(): void {
    this.observablesUnsubscribe.forEach(s => s.unsubscribe())
  }
}
